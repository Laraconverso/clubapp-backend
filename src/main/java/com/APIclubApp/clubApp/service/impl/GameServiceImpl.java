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
    public GameDTO saveGame(GameDTO gameDT) {

        //return gameRepository.save(game);
        Game game = modelMapper.map(gameDT, Game.class);
        game = gameRepository.save(game);
        return modelMapper.map(game, GameDTO.class);
    }

    @Override
    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public GameDTO updateGame(GameDTO gameDTO) {

        //return gameRepository.save(game);
        Game game = modelMapper.map(gameDTO, Game.class);
        game = gameRepository.save(game);
        return modelMapper.map(game, GameDTO.class);
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
