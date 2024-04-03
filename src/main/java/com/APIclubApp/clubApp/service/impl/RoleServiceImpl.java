package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.RoleDTO;
import com.APIclubApp.clubApp.exception.AlreadyExistsException;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.Role;
import com.APIclubApp.clubApp.repository.RoleRepository;
import com.APIclubApp.clubApp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .orElseThrow(() -> new NotFoundException("Role not found with ID: " + id));
    }

    @Override
    public Role saveRole(RoleDTO roleDTO) {
        Optional<Role> existingRole = roleRepository.findByRoleName(roleDTO.getRoleName());
        if (existingRole.isPresent()) {
            throw new AlreadyExistsException("Role already exists with name: " + roleDTO.getRoleName());
        }
        Role role = new Role();
        role.setRoleId(roleDTO.getRoleId());
        role.setRoleName(roleDTO.getRoleName());
        return roleRepository.save(role);

    }

    @Override
    public Role updateRole(RoleDTO roleDTO) {
        Role existingRole = roleRepository.findById(roleDTO.getRoleId())
                .orElseThrow(() -> new NotFoundException("Role not found with ID: " + roleDTO.getRoleId()));
        if (existingRole == null) {
            throw new NotFoundException("Role not found with ID: " + roleDTO.getRoleId());
        }
        Optional<Role> existingRoleWithSameName = roleRepository.findByRoleName(roleDTO.getRoleName());
        if (existingRoleWithSameName.isPresent()
                && !existingRoleWithSameName.get().getRoleName().equals(existingRole.getRoleId())) {
            throw new AlreadyExistsException("Role already exists with name: " + roleDTO.getRoleName());
        }
        existingRole.setRoleName(roleDTO.getRoleName());
        return roleRepository.save(existingRole);
    }


    @Override
    public void deleteRoleById(Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
        } else {
            throw new NotFoundException("Role not found with ID: " + id);
        }
    }
}
