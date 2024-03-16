package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.GameDTO;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Fixture;
import com.APIclubApp.clubApp.model.Game;
import com.APIclubApp.clubApp.repository.CategoryRepository;
import com.APIclubApp.clubApp.repository.FixtureRepository;
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
    private CategoryRepository categoryRepository;
    @Autowired
    private FixtureRepository fixtureRepository;
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
        return gameRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Game not found with ID: " + id));
    }

    @Override
    public Game updateGame(GameDTO gameDTO) {
        // Verificar si el juego a actualizar existe en la base de datos
        Game existingGame = gameRepository.findById(gameDTO.getGameId())
                .orElseThrow(() -> new NotFoundException("Game not found with ID: " + gameDTO.getGameId()));

        Category category = categoryRepository.findById(gameDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with ID: " + gameDTO.getCategoryId()));

        Fixture fixture = fixtureRepository.findById(gameDTO.getFixtureId())
                .orElseThrow(() -> new NotFoundException("Fixture not found with ID: " + gameDTO.getFixtureId()));

        // Mapear los datos del DTO al juego existente
        modelMapper.map(gameDTO, existingGame);
        existingGame.setFixture(fixture);
        existingGame.setCategory(category);
        // Guardar el juego actualizado en la base de datos y devolverlo
        return gameRepository.save(existingGame);

    }

    @Override
    public void deleteGame(Long id) {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
        } else {
            throw new NotFoundException("Game not found with ID: " + id);
        }    }
}
