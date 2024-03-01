package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.dto.GameDTO;
import com.APIclubApp.clubApp.model.Game;

import java.util.List;

public interface GameService {
    List<Game> listAllGames();

    GameDTO saveGame(GameDTO gameDTO);

    Game getGameById(Long id);

    GameDTO updateGame(GameDTO gameDTO);

    void deleteGame(Long id);
}

