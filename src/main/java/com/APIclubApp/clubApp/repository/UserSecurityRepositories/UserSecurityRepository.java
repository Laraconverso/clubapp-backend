package com.APIclubApp.clubApp.repository.UserSecurityRepositories;

import com.APIclubApp.clubApp.model.UserSecurityModels.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSecurityRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
