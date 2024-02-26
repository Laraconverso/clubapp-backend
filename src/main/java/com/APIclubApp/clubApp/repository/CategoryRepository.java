package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
