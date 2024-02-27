package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.model.Game;

import java.util.List;

public interface GameService {
    List<Game> listAllGames();

    Game saveGame(Game game);

    Game getGameById(Long id);

    Game updateGame(Game game);

    void deleteGame(Long id);
}

