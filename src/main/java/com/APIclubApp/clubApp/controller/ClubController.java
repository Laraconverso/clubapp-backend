package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.ClubDTO;
import com.APIclubApp.clubApp.model.Club;
import com.APIclubApp.clubApp.service.ClubService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/clubs")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @Operation(summary = "Listar todos los clubes")
    @GetMapping("/list")
    public ResponseEntity<List<ClubDTO>> listAllClubs() {
        return ResponseEntity.ok(clubService.listAllClubs());
    }

    @Operation(summary = "Obtener un club por su ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<ClubDTO> getClubById(@PathVariable Long id) {
        ClubDTO club = clubService.getClubById(id);
        if (club != null) {
            return ResponseEntity.ok(club);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @Operation(summary = "Guardar un club")
    @PostMapping("/save")
    public ResponseEntity<Club> saveClub(@RequestBody ClubDTO club) {
        return ResponseEntity.ok(clubService.saveClub(club));
    }

    @Operation(summary = "Actualizar un club")
    @PutMapping("/update")
    public ResponseEntity<Club> updateClub(@RequestBody ClubDTO club) {
        ResponseEntity<Club> response;
        if (club.getClubId() != null) {
            response = ResponseEntity.ok(clubService.saveClub(club));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @Operation(summary = "Borrar un club por su id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.ok().body("Deleted");
    }
}

