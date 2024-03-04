package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.dto.RoleDTO;
import com.APIclubApp.clubApp.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRoleById(Long id);
    Role saveRole(RoleDTO roleDTO);

    Role updateRole(RoleDTO roleDTO);

    void deleteRoleById(Long id);
}