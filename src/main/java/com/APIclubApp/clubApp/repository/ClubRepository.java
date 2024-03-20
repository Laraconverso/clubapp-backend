package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByClubName(String name);

}
