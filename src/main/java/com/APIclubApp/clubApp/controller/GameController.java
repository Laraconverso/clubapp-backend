package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.GameDTO;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.Game;
import com.APIclubApp.clubApp.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Operation(summary = "Lista todos los partidos")
    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<Game>> listAllGames() {
        List<Game> games= gameService.listAllGames();
        if (games.isEmpty()){
            System.out.println("Aún no hay partidos cargados en la base de datos.");
        }
        return ResponseEntity.ok(gameService.listAllGames());    }


    @Operation(summary = "Obtener un partido por ID")
    @GetMapping("/get/{id}")
    @PermitAll
    public ResponseEntity<?> getGameById(@PathVariable Long id) {
        Game game = gameService.getGameById(id);
        try {
            return ResponseEntity.ok(game);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Crear un partido")
    @PostMapping("/save")
    @PreAuthorize("hasRole('Coach', 'Admin')")
    public ResponseEntity<?> saveGame(@RequestBody GameDTO gameDTO) {
// Mapear GameDTO a Game
        Game game = modelMapper.map(gameDTO, Game.class);

        // Guardar el juego en el servicio
        Game savedGame = gameService.saveGame(gameDTO);

        // Verificar si se pudo guardar el juego
        // Si se guardó correctamente, devolver una respuesta con el juego guardado y el estado HTTP 200 OK
        return ResponseEntity.ok(savedGame);

    }

    @Operation(summary = "Actualizar un partido")
    @PutMapping("/update")
    @PreAuthorize("hasRole('Coach', 'Admin')")
    public ResponseEntity<?> updateGame( @RequestBody GameDTO gameDTO) {
        /*ResponseEntity<Game> response;
        if (gameService.getGameById(id) != null) {
            game.setGameId(id);
            response = ResponseEntity.ok(gameService.saveGame(game));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;*/
        // Mapear GameDTO a Game

        //Game game = modelMapper.map(gameDTO, Game.class);
        try {

            // Actualizar el juego en el servicio
            Game updatedGame = gameService.updateGame(gameDTO);

            return ResponseEntity.ok(updatedGame);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Obtener una lista de partidos por el ID de la Categoria")
    @GetMapping("/getGamesCategory/{id}")
    @PermitAll
    public ResponseEntity<List<Game>> getGamesByCategoryId(@PathVariable Long id) {
        List<Game> games = gameService.getGamesByCategoryId(id);
            return ResponseEntity.ok(games);
    }

    @Operation(summary = "Eliminar un partido por ID")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('Coach', 'Admin')")
    public ResponseEntity<String> deleteGame(@PathVariable Long id) {
        try {
            gameService.deleteGame(id);
            return ResponseEntity.ok().body("Game deleted successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

