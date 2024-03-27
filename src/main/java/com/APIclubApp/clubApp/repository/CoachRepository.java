package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach,Long> {
    Coach findByUserDni(String dni);
}
