package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.ClubDTO;
import com.APIclubApp.clubApp.exception.AlreadyExistsException;
import com.APIclubApp.clubApp.exception.NotFoundException;
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
    public ResponseEntity<?> getClubById(@PathVariable Long id) {
        ClubDTO club = clubService.getClubById(id);
        try {
            return ResponseEntity.ok(club);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @Operation(summary = "Guardar un club")
    @PostMapping("/save")
    public ResponseEntity<?> saveClub(@RequestBody ClubDTO club) {
        try {
            return ResponseEntity.ok(clubService.saveClub(club));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }    }

    @Operation(summary = "Actualizar un club")
    @PutMapping("/update")
    public ResponseEntity<?> updateClub(@RequestBody ClubDTO club) {
        try {
            return ResponseEntity.ok(clubService.updateClub(club));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @Operation(summary = "Borrar un club por su id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable Long id) {
        try {
            clubService.deleteClub(id);
            return ResponseEntity.ok().body("Club Deleted successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

