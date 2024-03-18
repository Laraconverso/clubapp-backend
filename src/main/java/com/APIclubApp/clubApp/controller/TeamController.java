package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.TeamDTO;
import com.APIclubApp.clubApp.exception.AlreadyExistsException;
import com.APIclubApp.clubApp.exception.AssociatedCategoriesException;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Team;
import com.APIclubApp.clubApp.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Listar todos los equipos")
    @GetMapping("/list")
    public ResponseEntity<List<Team>> listAllTeams() {
        List<Team> teams= teamService.listAllTeams();
        if (teams.isEmpty()) {
            System.out.println("Aún no hay equipos en la base de datos.");
        }
        return ResponseEntity.ok(teams);
    }

    @Operation(summary = "Obtener un equipo por su ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable Long id) {
        try {
            Team team = teamService.getTeamById(id);
            return ResponseEntity.ok(team);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Crear un equipo ")
    @PostMapping("/save")
    public ResponseEntity<?> saveTeam(@RequestBody TeamDTO team) {
        try {
            return ResponseEntity.ok(teamService.saveTeam(team));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un equipo")
    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<?> updateTeam(@RequestBody TeamDTO team){
        try {
            return ResponseEntity.ok(teamService.updateTeamById(team));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Borrar un equipo por su ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        try {
            teamService.deleteTeam(id);
            return ResponseEntity.ok().body("Team deleted successfully");
        } catch (AssociatedCategoriesException e) {
            System.out.println("Aún hay categorías asociadas a este equipo.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
