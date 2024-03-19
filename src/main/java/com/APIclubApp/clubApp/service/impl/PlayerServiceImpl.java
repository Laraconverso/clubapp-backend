package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.*;
import com.APIclubApp.clubApp.exception.AlreadyExistsException;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.*;
import com.APIclubApp.clubApp.repository.CategoryRepository;
import com.APIclubApp.clubApp.repository.ClubRepository;
import com.APIclubApp.clubApp.repository.PlayerRepository;
import com.APIclubApp.clubApp.repository.RoleRepository;
import com.APIclubApp.clubApp.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final ClubRepository clubRepository;


//    @Autowired
//    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;



    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, RoleRepository roleRepository, CategoryRepository categoryRepository, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public List<Player> listAllPlayers() {
        return playerRepository.findAll();
    }

    public Player savePlayer(PlayerDTO playerDto) {
        Player getPlayerByUserDni = playerRepository.findByUserDni(playerDto.getUserDni());
        if (getPlayerByUserDni != null) {
            throw new AlreadyExistsException("Player already exists with DNI: " + playerDto.getUserDni());
        }

        Player player = objectMapper.convertValue(playerDto, Player.class);
        Category category = categoryRepository.findById(playerDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));
        Role role = roleRepository.findByRoleName("Player")
                .orElseThrow(()->new NotFoundException("Role not found"));
        Club club = clubRepository.findById(playerDto.getClubId())
                .orElseThrow(() -> new NotFoundException("Club not found"));

        player.setCategory(category);
        player.setRole(role);
        player.setClub(club);

        return playerRepository.save(player);
    }


    //COMPLETO
    @Override
    public Player updatePlayer(PlayerDTO updatedPlayer) {
        if (playerRepository.existsById(updatedPlayer.getPlayerId())) {
            return savePlayer(updatedPlayer);
        } else {
            throw new NotFoundException("Player not found with id: " + updatedPlayer.getPlayerId());
        }
    }

    @Override
    public Player savePlayerForm(PlayerFormDTO player) {
        Player p = objectMapper.convertValue(player, Player.class);
        Category category = categoryRepository.findByCategoryName(player.getCategoryName());
        if(category == null ) throw new NotFoundException("Category not found");

        Role role = roleRepository.findByRoleName("Player")
                .orElseThrow(()->new NotFoundException("Role not found"));
        p.setRole(role);
        p.setCategory(category);
        p.setPlayerFeePaid(false);
        p.setPlayerPasswordChanged(false);
        return playerRepository.save(p);
    }

    @Override
    public Player updatePlayerAdmin(PlayerUpdateAdminDTO updatedPlayer) {
        // Convertimos el objeto DTO actualizado a un objeto Player
        Player player = objectMapper.convertValue(updatedPlayer, Player.class);

        // Buscamos al jugador en el repositorio por su ID
        Optional<Player> playerOptional = playerRepository.findById(updatedPlayer.getPlayerId());

        if (playerOptional.isPresent()) {
            // Si encontramos al jugador, actualizamos sus datos
            Player existingPlayer = playerOptional.get();
            // Actualizamos solo los campos que deben actualizarse
            if (updatedPlayer.getUserName() != null) {
                existingPlayer.setUserName(updatedPlayer.getUserName());
            }
            if (updatedPlayer.getUserLastname() != null) {
                existingPlayer.setUserLastname(updatedPlayer.getUserLastname());
            }
            if (updatedPlayer.getUserEmail() != null) {
                existingPlayer.setUserEmail(updatedPlayer.getUserEmail());
            }
            if (updatedPlayer.getUserAddress() != null) {
                existingPlayer.setUserAddress(updatedPlayer.getUserAddress());
            }
            // Guardamos el jugador actualizado en el repositorio
            return playerRepository.save(existingPlayer);
        } else {
            // Si no encontramos al jugador, lanzamos una excepción
            throw new NotFoundException("Player not found with ID: " + updatedPlayer.getPlayerId());
        }
    }

    public void deletePlayer(Long id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
        } else {
            throw new NotFoundException("Player not found with id: " + id);
        }
    }


    @Override
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Player not found with ID: " + id));

    }

    @Override
    public Player getPlayerByDNI(String dni) {
        Player player = playerRepository.findByUserDni(dni);
        if (player == null) {
            throw new NotFoundException("Player not found with DNI: " + dni);
        }
        return player;
    }

    @Override
    public Player updatePlayerPassword(PlayerChangePasswordDTO playerChangePasswordDTO) {
//        Optional<Player> optionalPlayer = playerRepository.findByid(playerChangePasswordDTO.getPlayerId());
        Player player = playerRepository.findByUserDni(playerChangePasswordDTO.getUserDni());
        if (player != null) {
//            Player player = optionalPlayer.get();
            player.setUserPassword(playerChangePasswordDTO.getUserPassword()); //cambiamos la contraseña
            player.setPlayerPasswordChanged(true); // Marcamos que la contraseña ha sido cambiada
            return playerRepository.save(player);
        } else {
            throw new NotFoundException("Player not found with DNI: " + playerChangePasswordDTO.getUserDni());
        }
    }

    @Override
    public Boolean getPlayerPasswordChanged(String dni) {
        Player player = playerRepository.findByUserDni(dni);
        if(player!=null){
           return player.getPlayerPasswordChanged();
        } else{
            throw new NotFoundException("Player not found with DNI" + dni);
        }
    }

    @Override
    public List<Object[]> getAllPlayerFeePaid(){
        List<Object[]> players= playerRepository.playersFeePaid();
        if(players == null){
            throw new NotFoundException("Players with paid fee not found " );
        }
        return  players;
    }



}
