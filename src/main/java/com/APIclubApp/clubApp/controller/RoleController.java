package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.RoleDTO;
import com.APIclubApp.clubApp.exception.NoChangesException;
import com.APIclubApp.clubApp.exception.AlreadyExistsException;
import com.APIclubApp.clubApp.exception.NotFoundException;
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
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(roleService.getRoleById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Crea un rol")
    @PostMapping("/save")
    public ResponseEntity<?> saveRole(@RequestBody RoleDTO roleDTO) {
        try {
            return ResponseEntity.ok(roleService.saveRole(roleDTO));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Actualiza un rol")
    @PutMapping("/update")
    public ResponseEntity<?> updateRole(@RequestBody RoleDTO roleDTO) {
        try {
            return ResponseEntity.ok(roleService.updateRole(roleDTO));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Elimina un rol por su ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRoleById(id);
            return ResponseEntity.ok().body("Role deleted successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}