package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.PlayerDTO;
import com.APIclubApp.clubApp.dto.PlayerFormDTO;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Club;
import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.repository.CategoryRepository;
import com.APIclubApp.clubApp.repository.ClubRepository;
import com.APIclubApp.clubApp.repository.PlayerRepository;
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

//    @Autowired
//    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;



    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, CategoryRepository categoryRepository, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.categoryRepository = categoryRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public List<Player> listAllPlayers() {
        return playerRepository.findAll();
    }

    public Player savePlayer(PlayerDTO playerDto) {
        Player player = objectMapper.convertValue(playerDto, Player.class);
        Category category = categoryRepository.findById(playerDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
//        Role role = roleRepository.findById(playerDto.getRoleId())
//                .orElseThrow(() -> new RuntimeException("Role not found"));
        Club club = clubRepository.findById(playerDto.getClubId())
                .orElseThrow(() -> new RuntimeException("Club not found"));

        player.setCategory(category);
//        player.setRole(role);
        player.setClub(club);

        return playerRepository.save(player);
    }


    //COMPLETO
    @Override
    public Player updatePlayer(PlayerDTO updatedPlayer) {
        if (playerRepository.existsById(updatedPlayer.getPlayerId())) {
            return savePlayer(updatedPlayer);
        } else {
            throw new RuntimeException("Player not found with id: " + updatedPlayer.getPlayerId());
        }
    }

    @Override
    public Player savePlayerForm(PlayerFormDTO player) {
        Player p = objectMapper.convertValue(player, Player.class);
        Category category = categoryRepository.findByCategoryName(player.getCategoryName());
        if(category == null ) throw new RuntimeException("Category not found");
        p.setCategory(category);
        p.setPlayerFeePaid(false);
        p.setPlayerPasswordChanged(false);
        return playerRepository.save(p);
    }

    @Override
    public Player updatePlayerForm(PlayerFormDTO updatedPlayer) {
        Player player = playerRepository.findByUserDni(updatedPlayer.getUserDni());
        if (player != null) {
           return savePlayerForm(updatedPlayer);
        } else {
            throw new RuntimeException("Player not found with DNI: " + updatedPlayer.getUserDni());
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
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).get();
    }

    @Override
    public Player getPlayerByDNI(String dni) {
        return playerRepository.findByUserDni(dni);
    }
}
