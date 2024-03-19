package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.*;
import com.APIclubApp.clubApp.exception.AlreadyExistsException;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.service.PlayerService;
import com.APIclubApp.clubApp.service.impl.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Operation(summary = "Obtiene un jugador por su ID")
    @GetMapping("/get/{id}")
    @PermitAll
    public ResponseEntity<?> getPlayerById(@PathVariable Long id){
        try {
            Player player = playerService.getPlayerById(id);
            return ResponseEntity.ok(playerService.getPlayerById(id)) ;
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Obtiene un jugador por su DNI")
    @GetMapping("/getByDni/{dni}")
    @PermitAll
    public ResponseEntity<?> getPlayerById(@PathVariable String dni){
        try {
            Player player = playerService.getPlayerByDNI(dni);
            return ResponseEntity.ok(player);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Obtiene una lista de jugadores")
    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<Player>> getAllPlayers(){
        List<Player> players= playerService.listAllPlayers();
        if (players.isEmpty()){
            System.out.println("Aún no hay Jugadores en la base de datos.");
        }
        return ResponseEntity.ok(playerService.listAllPlayers());
    }

   @PostMapping("/saveComplete")
    @PermitAll
    public ResponseEntity<?> savePlayer(@RequestBody PlayerDTO player){
       try {
           player.setUserPassword(passwordEncoder.encode(player.getUserPassword()));
            return ResponseEntity.ok(playerService.savePlayer(player));
       } catch (AlreadyExistsException e) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
       }
    }

    @Operation(summary = "Crea un jugador")
    @PostMapping("/save")
    @PermitAll
    public ResponseEntity<?> savePlayerForm(@RequestBody PlayerFormDTO player){
        //descomentar para encriptar
        //String passWEncrypt= passwordEncoder.encode(player.getUserPassword());
        //player.setUserPassword(passWEncrypt);
        try {
            return ResponseEntity.ok(playerService.savePlayerForm(player));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Elimina un jugador por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id){
        try {
            playerService.deletePlayer(id);
            return ResponseEntity.ok().body("Player deleted successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Actualiza un jugador")
    @PutMapping("/update")
    public ResponseEntity<?> updatePlayer(@RequestBody PlayerDTO player){
        try {
            Player updatePlayer = playerService.updatePlayer(player);
            return ResponseEntity.ok(updatePlayer);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @Operation(summary = "modificar datos basicos de usuario jugador por parte del admin")
    @PutMapping("/update/form") public ResponseEntity<?> updatePlayerAdmin(@RequestBody PlayerUpdateAdminDTO player){
        try {
            // Si se encuentra el jugador, llamar al servicio para actualizarlo
            Player updatedPlayer = playerService.updatePlayerAdmin(player);
            // Devolver una respuesta con el jugador actualizado y código HTTP 200
            return ResponseEntity.ok(updatedPlayer);
        } catch (NotFoundException e) {
            // Si el jugador no se encuentra, devolver una respuesta HTTP 404 con un mensaje de error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
  }
    @Operation(summary = "modificar contraseña de usuario jugador")
    @PutMapping("/update/password")
    public ResponseEntity<?> updatePlayerChangePassword(@RequestBody PlayerChangePasswordDTO player){
        try {
//        String passWEncrypt= passwordEncoder.encode(player.getUserPassword());
//        player.setUserPassword(passWEncrypt);
            //player.setClubId(player.getClubId());
            Player updatedPlayer = playerService.updatePlayerPassword(player);
            return ResponseEntity.ok(updatedPlayer);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Obtiene un booleano que le indica si la contraseña ha sido modificada")
    @GetMapping("/getPasswordChanged/{dni}")
    public ResponseEntity<Object> getPlayerPasswordChanged(@PathVariable String dni){
        try {
            boolean passwordChanged = playerService.getPlayerPasswordChanged(dni);
            return ResponseEntity.ok(passwordChanged);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Obtiene una lista con los jugadores que han pagado su cuota")
    @GetMapping("/getPlayersPaidFee")
    public ResponseEntity<Object> getPlayersPaidFee(){
        try {
            List<Object[]> players = playerService.getAllPlayerFeePaid();
            return ResponseEntity.ok(players);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Obtiene un reporte una lista con los jugadores que han pagado su cuota")
    @GetMapping("/getPlayersPaidFeeReport")
    public ResponseEntity<byte[]> getPlayersPaidFeeReport() {
        List<Object[]> players = playerService.getAllPlayerFeePaid();
        byte[] pdfBytes = reportService.generatePdfReport(players);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "players_report.pdf");
        headers.setContentLength(pdfBytes.length);

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }



}