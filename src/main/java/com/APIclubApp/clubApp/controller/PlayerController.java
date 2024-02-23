package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.service.PlayerService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    //private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

    @GetMapping("/listAll")
    @PermitAll
    public ResponseEntity<List<Player>> getAllPlayers(){
        return ResponseEntity.ok(playerService.listAllPlayers());
    }

    @PostMapping("/save")
    @PermitAll
    public ResponseEntity<Player> savePlayer(@RequestBody Player player){
        //ROL
        //Role role = roleRepository.findByNombre("User");
        //player.setRole(role);
//        player.setUserPassword(passwordEncoder.encode(player.getUserPassword()));
        return ResponseEntity.ok(playerService.savePlayer(player));
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id){
        if (playerService.getPlayerById(Long.valueOf(id))!=null){
           playerService.deletePlayer(Long.valueOf(id));
        }else {
            System.out.println("Player does not exist");
        }
    }

    @PutMapping()
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player){
        Player player1 = playerService.getPlayerById(player.getIdPlayer());
        if ( player1!= null && player1.getIdPlayer() != null)
            return ResponseEntity.ok(playerService.updatePlayer(player));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
