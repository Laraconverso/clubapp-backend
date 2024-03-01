package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.CoachDTO;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.service.CoachService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/coaches")
public class CoachController {
    @Autowired
    private CoachService coachService;

    @GetMapping("/list")
    public ResponseEntity<List<CoachDTO>> listAllCoaches(){
        return ResponseEntity.ok(coachService.listAllCoaches());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CoachDTO> getCoachById(@PathVariable Long id){
        ResponseEntity<CoachDTO> response;

        CoachDTO coach = coachService.getCoachById(id);
        if (coach != null) {
            response = ResponseEntity.ok(coach);
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PostMapping("/save")
    public ResponseEntity<Coach> saveCoach(@RequestBody CoachDTO coach){
        return ResponseEntity.ok(coachService.saveCoach(coach));
    }

    /*@PutMapping("/update/{id}")
    public ResponseEntity<Coach> updateCoach(@PathVariable Long id, @RequestBody Coach coach){
        ResponseEntity<Coach> response;

        if (coachService.getCoachById(id) != null){
            coach.setId(id); // Aseguramos que el coach tenga el mismo ID que el path variable
            response = ResponseEntity.ok(coachService.saveCoach(coach));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }*/

    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<Coach> updateCategory(@RequestBody CoachDTO coach){
        ResponseEntity<Coach> response;
        if (coach.getCoachNumber() != null && coachService.getCoachById(coach.getCoachNumber()) != null){
            response = ResponseEntity.ok(coachService.saveCoach(coach));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCoach(@PathVariable Long id){
        coachService.deleteCoach(id);
        return ResponseEntity.ok().body("Deleted");
    }

}