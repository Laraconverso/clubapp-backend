package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.FixtureDTO;
import com.APIclubApp.clubApp.dto.GameDTO;
import com.APIclubApp.clubApp.model.Game;
import com.APIclubApp.clubApp.service.GameService;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper; // Necesitamos una instancia de ModelMapper para convertir entre GameDTO y Game
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
    public ResponseEntity<Game> saveGame(@RequestBody GameDTO gameDTO) {
// Mapear GameDTO a Game
        Game game = modelMapper.map(gameDTO, Game.class);

        // Guardar el juego en el servicio
        Game savedGame = gameService.saveGame(gameDTO);

        // Verificar si se pudo guardar el juego
        if (savedGame != null) {
            // Si se guardó correctamente, devolver una respuesta con el juego guardado y el estado HTTP 200 OK
            return ResponseEntity.ok(savedGame);
        } else {
            // Si no se pudo guardar, devolver una respuesta con el estado HTTP 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Game> updateGame( @RequestBody GameDTO gameDTO) {
        /*ResponseEntity<Game> response;
        if (gameService.getGameById(id) != null) {
            game.setGameId(id);
            response = ResponseEntity.ok(gameService.saveGame(game));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;*/
        // Mapear GameDTO a Game
        Game game = modelMapper.map(gameDTO, Game.class);

        // Actualizar el juego en el servicio
        Game updatedGame = gameService.updateGame(gameDTO);

        // Verificar si se pudo actualizar el juego
        if (updatedGame != null) {
            // Si se actualizó correctamente, devolver una respuesta con el juego actualizado y el estado HTTP 200 OK
            return ResponseEntity.ok(updatedGame);
        } else {
            // Si no se pudo actualizar, devolver una respuesta con el estado HTTP 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.ok().body("Deleted");
    }
}

