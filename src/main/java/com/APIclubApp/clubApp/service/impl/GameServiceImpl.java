package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.model.Game;
import com.APIclubApp.clubApp.repository.GameRepository;
import com.APIclubApp.clubApp.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> listAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public Game updateGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
