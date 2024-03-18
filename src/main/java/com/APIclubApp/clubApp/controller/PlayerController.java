package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.PlayerChangePasswordDTO;
import com.APIclubApp.clubApp.dto.PlayerDTO;
import com.APIclubApp.clubApp.dto.PlayerFormDTO;
import com.APIclubApp.clubApp.dto.PlayerUpdateAdminDTO;
import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.model.Role;
import com.APIclubApp.clubApp.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private BCryptPasswordEncoder passwordEncoder;

    @Operation(summary = "Obtiene un jugador por su ID")
    @GetMapping("/get/{id}")
    @PermitAll
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id){
        ResponseEntity<Player> response;

        if (playerService.getPlayerById(Long.valueOf(id))!=null){
            response = ResponseEntity.ok(playerService.getPlayerById(Long.valueOf(id))) ;
        }else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @Operation(summary = "Obtiene un jugador por su DNI")
    @GetMapping("/getByDni/{dni}")
    @PermitAll
    public ResponseEntity<Player> getPlayerById(@PathVariable String dni){
        ResponseEntity<Player> response;
        if (playerService.getPlayerByDNI(String.valueOf(dni))!=null){
            response = ResponseEntity.ok(playerService.getPlayerByDNI(String.valueOf(dni)));
        }else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @Operation(summary = "Obtiene una lista de jugadores")
    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<Player>> getAllPlayers(){
        return ResponseEntity.ok(playerService.listAllPlayers());
    }

   @PostMapping("/saveComplete")
    @PermitAll
    public ResponseEntity<Player> savePlayer(@RequestBody PlayerDTO player){
        player.setUserPassword(passwordEncoder.encode(player.getUserPassword()));
        return ResponseEntity.ok(playerService.savePlayer(player));
    }

    @Operation(summary = "Crea un jugador")
    @PostMapping("/save")
    @PermitAll
    public ResponseEntity<Player> savePlayerForm(@RequestBody PlayerFormDTO player){
        //descomentar para encriptar
        //String passWEncrypt= passwordEncoder.encode(player.getUserPassword());
        //player.setUserPassword(passWEncrypt);


        return ResponseEntity.ok(playerService.savePlayerForm(player));
    }

    @Operation(summary = "Elimina un jugador por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id){
        if (playerService.getPlayerById(Long.valueOf(id))!=null){
            playerService.deletePlayer(Long.valueOf(id));
            return ResponseEntity.ok().body("Deleted");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Actualiza un jugador")
    @PutMapping("/update")
    public ResponseEntity<Player> updatePlayer(@RequestBody PlayerDTO player){
        Player player1 = playerService.getPlayerById(player.getPlayerId());
        if ( player1!= null && player1.getPlayerId() != null)
            return ResponseEntity.ok(playerService.updatePlayer(player));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
    @Operation(summary = "modificar datos basicos de usuario jugador por parte del admin")
    @PutMapping("/update/form") public ResponseEntity<Player> updatePlayerAdmin(@RequestBody PlayerUpdateAdminDTO player){
        // Obtener el jugador existente por su ID
        Player player1 = playerService.getPlayerById(player.getPlayerId());

        if (player1 != null && player1.getPlayerId() != null) {
            // Si se encuentra el jugador, llamar al servicio para actualizarlo
            Player updatedPlayer = playerService.updatePlayerAdmin(player);
            // Devolver una respuesta con el jugador actualizado y código HTTP 200
            return ResponseEntity.ok(updatedPlayer);
        } else {
            // Si el jugador no se encuentra, devolver una respuesta HTTP 404 con un mensaje de error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Se podría añadir un mensaje de error en el cuerpo
        }
  }
    @Operation(summary = "modificar contraseña de usuario jugador")
    @PutMapping("/update/password")
    public ResponseEntity<Player> updatePlayerChangePassword(@RequestBody PlayerChangePasswordDTO player){
//        String passWEncrypt= passwordEncoder.encode(player.getUserPassword());
//        player.setUserPassword(passWEncrypt);
        //player.setClubId(player.getClubId());
        Player updatedPlayer = playerService.updatePlayerPassword(player);
        if (updatedPlayer != null) {
            return ResponseEntity.ok(updatedPlayer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @Operation(summary = "Obtiene un booleano que le indica si la contraseña ha sido modificada")
    @GetMapping("/getPasswordChanged/{dni}")
    public ResponseEntity<Object> getPlayerPasswordChanged(@PathVariable String dni){
        if (playerService.getPlayerByDNI(String.valueOf(dni))!=null){
            return ResponseEntity.ok(playerService.getPlayerPasswordChanged(String.valueOf(dni)));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


}