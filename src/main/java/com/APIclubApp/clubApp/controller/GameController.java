package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.FixtureDTO;
import com.APIclubApp.clubApp.dto.GameDTO;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.model.Game;
import com.APIclubApp.clubApp.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<List<Game>> listAllGames() {
        List<Game> games= gameService.listAllGames();
        if (games.isEmpty()){
            System.out.println("Aún no hay partidos cargados en la base de datos.");
        }
        return ResponseEntity.ok(gameService.listAllGames());    }


    @Operation(summary = "Obtener un partido por ID")
    @GetMapping("/get/{id}")
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
    public ResponseEntity<List<Game>> getGamesByCategoryId(@PathVariable Long id) {
        List<Game> games = gameService.getGamesByCategoryId(id);
            return ResponseEntity.ok(games);
    }

    @Operation(summary = "Eliminar un partido por ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Long id) {
        try {
            gameService.deleteGame(id);
            return ResponseEntity.ok().body("Game deleted successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Obtener un partido por ID y su fixture asociado")
    @GetMapping("/getGameFix/{gameId}")
    public ResponseEntity<?> getGameByIdFix(@PathVariable Long gameId) {
       /* Optional<Game> game = gameService.findGameWithFixtureById(gameId);
        try {
            return ResponseEntity.ok(game);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }*/
        try {
            Optional<Game> game = gameService.findGameWithFixtureById(gameId);
        if (game.isPresent()) {
            // Obtener el objeto Coach
            Game gameObj = game.get();

            // Crear un mapa para almacenar la información del game y su fixture
            Map<String, Object> response = new HashMap<>();
            response.put("game", game);
            response.put("fixture", gameObj.getFixture().getFixtureId()); // Agregar la fixture al mapa

            return ResponseEntity.ok(response); // Devolver el mapa con el coach y la categoría
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found with idGame: " + gameId);
        }
    } catch (NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}


}

