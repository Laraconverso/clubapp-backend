package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.RoleDTO;
import com.APIclubApp.clubApp.model.Role;
import com.APIclubApp.clubApp.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Operation(summary = "Lista todos los roles")
    @GetMapping("/list")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @Operation(summary = "Obtiene un rol por su ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Role role = roleService.getRoleById(id);
        if (role != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Crea un rol")
    @PostMapping("/save")
    public ResponseEntity<Role> saveRole(@RequestBody RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleId(roleDTO.getRoleId());
        role.setRoleName(roleDTO.getRoleName());

        Role savedRole = roleService.saveRole(roleDTO);

        if (savedRole != null) {
            return ResponseEntity.ok(savedRole);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Actualiza un rol")
    @PutMapping("/update")
    public ResponseEntity<Role> updateRole(@RequestBody RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleId(roleDTO.getRoleId());
        role.setRoleName(roleDTO.getRoleName());

        Role updatedRole = roleService.updateRole(role);

        if (updatedRole != null) {
            return ResponseEntity.ok(updatedRole);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Elimina un rol por su ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        if (roleService.getRoleById(id) != null) {
            roleService.deleteRoleById(id);
            return ResponseEntity.ok().body("Role was deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}