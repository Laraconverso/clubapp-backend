package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.dto.PlayerFormDTO;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String name);

    //PlayerFormDTO PlayersByCategoryName(String categoryName);
    @Query("SELECT c FROM Category c JOIN FETCH c.coach WHERE c.coach.coachNumber = :coachNumber")
    Optional<Category> findByCoachNumber(@Param("coachNumber") Long coachNumber);
}
