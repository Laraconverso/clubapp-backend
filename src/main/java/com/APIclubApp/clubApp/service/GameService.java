package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.dto.GameDTO;
import com.APIclubApp.clubApp.model.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> listAllGames();

    Game saveGame(GameDTO gameDTO);

    Game getGameById(Long id);

    Game updateGame(GameDTO gameDTO);

    void deleteGame(Long id);

    List<Game> getGamesByCategoryId(Long idCategory);

    Optional<Game> findGameWithFixtureById(Long gameId);
}

