package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.PlayerDTO;
import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.repository.PlayerRepository;
import com.APIclubApp.clubApp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> listAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player savePlayer(PlayerDTO player) {
        Player p = new Player();
        p.setPlayerId(player.getPlayerId());
        p.setPlayerPosition(player.getPlayerPosition());
        p.setPlayerBirthdate(player.getPlayerBirthdate());
        p.setPlayerFeePaid(player.getPlayerFeePaid());


        //p.setCategory(player.getCategoryId());
        return playerRepository.save(p);
    }

    @Override
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).get();
    }


    @Override
    public Player updatePlayer(Player updatedPlayer) {
        if (playerRepository.existsById(updatedPlayer.getPlayerId())) {
            return playerRepository.save(updatedPlayer);
        } else {
            throw new RuntimeException("Player not found with id: " + updatedPlayer.getPlayerId());
        }
    }

    public void deletePlayer(Long id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Player not found with id: " + id);
        }
    }

    @Override
    public Player getPlayerByDNI(String dni) {
        return playerRepository.findByUserDni(dni);
    }
}
