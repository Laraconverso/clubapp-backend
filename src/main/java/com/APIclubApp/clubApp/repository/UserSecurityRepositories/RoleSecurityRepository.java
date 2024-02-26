package com.APIclubApp.clubApp.repository.UserSecurityRepositories;

import com.APIclubApp.clubApp.model.UserSecurityModels.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleSecurityRepository extends JpaRepository<RoleEntity, Long> {
}
