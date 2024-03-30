package com.APIclubApp.clubApp.security.usersecurity.repository;

import com.APIclubApp.clubApp.security.usersecurity.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleSecurityRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRolename(String name);

}