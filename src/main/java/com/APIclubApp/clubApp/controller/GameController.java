package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.model.Game;
import com.APIclubApp.clubApp.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/list")
    public ResponseEntity<List<Game>> listAllGames() {
        return ResponseEntity.ok(gameService.listAllGames());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Game game = gameService.getGameById(id);
        if (game != null) {
            return ResponseEntity.ok(game);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Game> saveGame(@RequestBody Game game) {
        return ResponseEntity.ok(gameService.saveGame(game));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        ResponseEntity<Game> response;
        if (gameService.getGameById(id) != null) {
            game.setGameId(id);
            response = ResponseEntity.ok(gameService.saveGame(game));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.ok().body("Deleted");
    }
}

