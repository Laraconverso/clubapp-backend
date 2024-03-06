package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.dto.PlayerFormDTO;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String name);
    //PlayerFormDTO PlayersByCategoryName(String categoryName);
}
