package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByUserDni(String dni);

    @Query(value="select p.* from clubapp.players p join clubapp.categories c on p.category_id = c.category_id where c.category_id=?1", nativeQuery = true)
    List<Player> findAllByCategoryId(Long category_id);

    @Query(value="SELECT player_id, user_name, user_lastname FROM clubapp.players WHERE player_fee_paid=true", nativeQuery = true)
    List<Object[]> playersFeePaid();

    /*@Query(value="SELECT p.category.category_name, COUNT(p) AS playerCount FROM clubapp.players p GROUP BY p.category.category_id", nativeQuery = true)
    List<Object[]> countPlayersByCategory();*/

    @Query(value = "SELECT c.category_name, COUNT(p.player_id) AS playerCount " +
            "FROM clubapp.players p " +
            "JOIN clubapp.categories c ON p.category_id = c.category_id " +
            "GROUP BY c.category_name",
            nativeQuery = true)
    List<Object[]> countPlayersByCategory();


    /*@Query(value="select p.player_id, p.user_name, p.user_lastname FROM Player p WHERE p.player_fee_paid = true")
    List<PlayerDTOreport> playersFeePaid();*/


    /*@Query(value="SELECT clubapp.PlayerDTOreport(p.playerId, p.name, p.lastname) FROM Player p WHERE p.playerFeePaid = true")
    List<PlayerDTOreport> playersFeePaid();*/


}