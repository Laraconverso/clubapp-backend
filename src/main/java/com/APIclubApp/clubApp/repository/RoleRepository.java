package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.model.Game;
import com.APIclubApp.clubApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String name);

}
