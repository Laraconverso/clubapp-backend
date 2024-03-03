package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.dto.GameDTO;
import com.APIclubApp.clubApp.model.Game;

import java.util.List;

public interface GameService {
    List<Game> listAllGames();

    Game saveGame(GameDTO gameDTO);

    Game getGameById(Long id);

    Game updateGame(GameDTO gameDTO);

    void deleteGame(Long id);
}

