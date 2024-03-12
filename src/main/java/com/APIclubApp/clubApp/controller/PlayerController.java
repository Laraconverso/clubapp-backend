package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.PlayerDTO;
import com.APIclubApp.clubApp.dto.PlayerFormDTO;
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

    @Operation(summary = "Obtiene un jugador por su ID")
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

    @Operation(summary = "Obtiene un jugador por su ID")
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
        String passWEncrypt= passwordEncoder.encode(player.getUserPassword());
        player.setUserPassword(passWEncrypt);
        //PlayerFormDTO savedPlayer= playerService.savePlayerForm(player);

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
    @PutMapping("update")
    public ResponseEntity<Player> updatePlayer(@RequestBody PlayerDTO player){
        Player player1 = playerService.getPlayerById(player.getPlayerId());
        if ( player1!= null && player1.getPlayerId() != null)
            return ResponseEntity.ok(playerService.updatePlayer(player));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

//    @PutMapping("/update/form")
//    public ResponseEntity<Player> updatePlayerForm(@RequestBody PlayerFormDTO player){
//        Player player1 = playerService.getPlayerByDNI(player.getUserDni());
//        if (player1!= null && player1.getPlayerId() != null)
//            return ResponseEntity.ok(playerService.updatePlayerForm(player));
//        else
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//
//    }

}