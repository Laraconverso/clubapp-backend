package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.ClubDTO;
import com.APIclubApp.clubApp.model.Club;
import com.APIclubApp.clubApp.service.ClubService;
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

    @GetMapping("/list")
    public ResponseEntity<List<ClubDTO>> listAllClubs() {
        return ResponseEntity.ok(clubService.listAllClubs());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ClubDTO> getClubById(@PathVariable Long id) {
        ClubDTO club = clubService.getClubById(id);
        if (club != null) {
            return ResponseEntity.ok(club);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Club> saveClub(@RequestBody ClubDTO club) {
        return ResponseEntity.ok(clubService.saveClub(club));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody ClubDTO club) {
        ResponseEntity<Club> response;
        if (clubService.getClubById(id) != null) {
            club.setClubId(id);
            response = ResponseEntity.ok(clubService.saveClub(club));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.ok().body("Deleted");
    }
}

