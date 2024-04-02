package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findById(Long id);

    @Query("SELECT g FROM Game g WHERE g.category.id = :categoryId")
    List<Game> getGamesByCategoryId(Long categoryId);

    @Query(value="SELECT g.*, fix.fixture_id AS fixture_id FROM clubapp.games g JOIN clubapp.fixtures fix ON g.id_fixture = fix.fixture_id WHERE g.game_id =?1", nativeQuery = true)
    Optional<Game> findGameWithFixtureById(Long gameId);

    /*@Query("SELECT g FROM Game g LEFT JOIN FETCH g.fixture f WHERE g.gameId = :gameId")
    Optional<Game> findGameWithFixtureById(Long gameId);*/

}
