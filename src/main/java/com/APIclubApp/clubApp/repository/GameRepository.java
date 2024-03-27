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

}
