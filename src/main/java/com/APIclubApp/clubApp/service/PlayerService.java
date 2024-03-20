package com.APIclubApp.clubApp.service;
import com.APIclubApp.clubApp.dto.*;
import com.APIclubApp.clubApp.model.Player;

import java.util.List;

public interface PlayerService {

    List<Player> listAllPlayers();

    Player savePlayer(PlayerDTO player);
    Player savePlayerForm(PlayerFormDTO player);

    Player getPlayerById(Long id);

    Player updatePlayer(PlayerDTO player);
    Player updatePlayerAdmin(PlayerUpdateAdminDTO player);

    void deletePlayer(Long id);

    Player getPlayerByDNI(String dni);

    Player updatePlayerPassword(PlayerChangePasswordDTO player);

    Boolean getPlayerPasswordChanged(String dni);

    List<Object[]> getAllPlayerFeePaid();

    PlayersMetricsDTO getPlayersMetrics();

    List<Object[]> countPlayersByCategory();

}
