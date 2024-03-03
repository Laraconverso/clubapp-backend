package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.TeamDTO;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Team;
import com.APIclubApp.clubApp.service.TeamService;
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

    @GetMapping("/list")
    public ResponseEntity<List<Team>> listAllTeams() {
        return ResponseEntity.ok(teamService.listAllTeams());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        return team != null ? ResponseEntity.ok(team) : ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Team> saveTeam(@RequestBody TeamDTO team) {
        return ResponseEntity.ok(teamService.saveTeam(team));
    }

    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<Team> updateTeam(@RequestBody TeamDTO team){
        ResponseEntity<Team> response;
        if (team.getTeamId() != null && teamService.getTeamById(team.getTeamId()) != null){
            response = ResponseEntity.ok(teamService.saveTeam(team));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
