package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.PlayerDTO;
import com.APIclubApp.clubApp.dto.PlayerFormDTO;
import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.repository.CategoryRepository;
import com.APIclubApp.clubApp.repository.ClubRepository;
import com.APIclubApp.clubApp.repository.PlayerRepository;
import com.APIclubApp.clubApp.repository.TeamRepository;
import com.APIclubApp.clubApp.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    //private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final ClubRepository clubRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, CategoryRepository categoryRepository, ClubRepository clubRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.categoryRepository = categoryRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public List<Player> listAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player savePlayer(PlayerDTO player) {
        Player p = modelMapper.map(player, Player.class);
        return playerRepository.save(p);
    }

    @Override
    public Player savePlayerForm(PlayerFormDTO player) {
        Player p = modelMapper.map(player,Player.class);
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
    public Player updatePlayer(PlayerDTO updatedPlayer) {
        if (playerRepository.existsById(updatedPlayer.getPlayerId())) {
            Player p =new Player();
            p.setPlayerId(updatedPlayer.getPlayerId());
            p.setPlayerPosition(updatedPlayer.getPlayerPosition());
            p.setPlayerBirthdate(updatedPlayer.getPlayerBirthdate());
            p.setPlayerFeePaid(updatedPlayer.getPlayerFeePaid());
            p.setCategory(categoryRepository.getReferenceById(updatedPlayer.getCategoryId()));
            p.setUserName(updatedPlayer.getUserName());
            p.setUserLastname(updatedPlayer.getUserLastname());
            p.setUserDni(updatedPlayer.getUserDni());
            p.setUserEmail(updatedPlayer.getUserEmail());
            p.setUserPassword(updatedPlayer.getUserPassword());
            //p.setRole(rolerepository.....);
            p.setClub(clubRepository.getReferenceById(updatedPlayer.getClubId()));
            return playerRepository.save(p);
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
