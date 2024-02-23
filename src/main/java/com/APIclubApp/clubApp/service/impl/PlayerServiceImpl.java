package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.repository.PlayerRepository;
import com.APIclubApp.clubApp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).get();
    }


    @Override
    public Player updatePlayer(Player updatedPlayer) {
        if (playerRepository.existsById(updatedPlayer.getIdPlayer())) {
            return playerRepository.save(updatedPlayer);
        } else {
            throw new RuntimeException("Player not found with id: " + updatedPlayer.getIdPlayer());
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
