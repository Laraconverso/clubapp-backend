package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByUserDni(String dni);

}