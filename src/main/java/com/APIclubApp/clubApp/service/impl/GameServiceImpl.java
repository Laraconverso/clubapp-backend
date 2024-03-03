package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.EmployeeDTO;
import com.APIclubApp.clubApp.dto.GameDTO;
import com.APIclubApp.clubApp.model.Employee;
import com.APIclubApp.clubApp.model.Game;
import com.APIclubApp.clubApp.repository.GameRepository;
import com.APIclubApp.clubApp.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    //ObjectMapper objectMapper;
    public ModelMapper modelMapper; // Necesitamos una instancia de ModelMapper

    @Override
    public List<Game> listAllGames() {

        return gameRepository.findAll();
    }

    @Override
    public Game saveGame(GameDTO gameDTO) {
        Game game = modelMapper.map(gameDTO, Game.class);
        return gameRepository.save(game);
    }

    @Override
    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public Game updateGame(GameDTO gameDTO) {
        // Verificar si el juego a actualizar existe en la base de datos
        Game existingGame = gameRepository.findById(gameDTO.getGameId()).orElse(null);

        // Si el juego existe, actualizar sus atributos con los del DTO
        if (existingGame != null) {
            // Mapear los datos del DTO al juego existente
            modelMapper.map(gameDTO, existingGame);

            // Guardar el juego actualizado en la base de datos y devolverlo
            return gameRepository.save(existingGame);
        } else {
            // Manejar el caso en que el juego no exista (puedes lanzar una excepción o devolver null, dependiendo de tus requerimientos)
            return null;
        }
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
