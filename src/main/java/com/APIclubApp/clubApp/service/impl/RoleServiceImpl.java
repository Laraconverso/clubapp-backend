package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.RoleDTO;
import com.APIclubApp.clubApp.exception.RoleNotFoundException;
import com.APIclubApp.clubApp.model.Role;
import com.APIclubApp.clubApp.repository.RoleRepository;
import com.APIclubApp.clubApp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with ID: " + id));
    }

    @Override
    public Role saveRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleId(roleDTO.getRoleId());
        role.setRoleName(roleDTO.getRoleName());
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(RoleDTO roleDTO) {
        Role existingRole = roleRepository.findById(roleDTO.getRoleId()).orElse(null);
        if (existingRole != null) {
            existingRole.setRoleName(roleDTO.getRoleName());
            return roleRepository.save(existingRole);
        } else {
            throw new RoleNotFoundException("Role not found with ID: " + roleDTO.getRoleId());
        }
    }

    @Override
    public void deleteRoleById(Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
        } else {
            throw new RoleNotFoundException("Role not found with ID: " + id);
        }
    }
}
