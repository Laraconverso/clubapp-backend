package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.PlayerDTO;
import com.APIclubApp.clubApp.dto.PlayerFormDTO;
import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.repository.PlayerRepository;
import com.APIclubApp.clubApp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    //private final RoleRepository roleRepository;

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


        //ROL
//        Role role = roleRepository.findByNombre("User");
//        player.setRoleId(role.getRoleId());
        //p.setCategory(player.getCategoryId());
        return playerRepository.save(p);
    }

    @Override
    public Player savePlayerForm(PlayerFormDTO player) {
        Player p = new Player();
        p.setPlayerId(player.getPlayerId());

        //ROL
//        Role role = roleRepository.findByNombre("User");
//        player.setRoleId(role.getRoleId());
        //p.setCategory(player.getCategoryId());
        return playerRepository.save(p);
    }


    //el normal
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
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
