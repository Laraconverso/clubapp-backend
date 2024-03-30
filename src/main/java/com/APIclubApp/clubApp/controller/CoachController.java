package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.CoachCategryDTO;
import com.APIclubApp.clubApp.dto.CoachDTO;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.service.CategoryService;
import com.APIclubApp.clubApp.service.CoachService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/coaches")
public class CoachController {
    @Autowired
    private CoachService coachService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Operation(summary = "Listar todos los coaches/dts")
    @GetMapping("/list")
    @PreAuthorize("hasRole('Coach', 'Admin', 'Player')")
    public ResponseEntity<List<Coach>> listAllCoaches(){
        return ResponseEntity.ok(coachService.listAllCoaches());
    }


    @Operation(summary = "Obtener un coach/dt por su ID")
    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('Coach', 'Admin', 'Player')")
    public ResponseEntity<Coach> getCoachById(@PathVariable Long id){
        ResponseEntity<Coach> response;

        Coach coach = coachService.getCoachById(id);
        if (coach != null) {
            response = ResponseEntity.ok(coach);
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }


    @Operation(summary = "Obtener un coach/dt por su DNI")
    @GetMapping("/getByDni/{dni}")
    @PreAuthorize("hasRole('Coach', 'Admin', 'Player')")
    public ResponseEntity<Coach> getCoachByDNI(@PathVariable String dni){
        ResponseEntity<Coach> response;

        Coach coach = coachService.getCoachByDNI(dni);
        if (coach != null) {
            response = ResponseEntity.ok(coach);
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @Operation(summary = "Crear un coach")
    @PostMapping("/save")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Coach> saveCoach(@RequestBody CoachDTO coach){
        //descomentar para encriptar
        //String passWEncrypt= passwordEncoder.encode(coach.getUserPassword());
        //coach.setUserPassword(passWEncrypt);
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

    @Operation(summary = "Actualizar un coach")
    @PutMapping("/update")
    @PreAuthorize("hasRole('Admin', 'Coach')")
    public ResponseEntity<Coach> updateCoach(@RequestBody CoachDTO coach){
        ResponseEntity<Coach> response;
        if (coach.getCoachNumber() != null /*&& coachService.getCoachById(coach.getCoachNumber()) != null*/){
            response = ResponseEntity.ok(coachService.saveCoach(coach));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @Operation(summary = "Eliminar un coach/dt por su ID")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deleteCoach(@PathVariable Long id){
        coachService.deleteCoach(id);
        return ResponseEntity.ok().body("Deleted");
    }

    @Operation(summary = "Contar la cantidad de coaches guardados en la BBDD")
    @GetMapping("/metrics")
    public Long getCoachesCount() {
        return coachService.getCoachesCount();
    }


    @Operation(summary = "Actualiza la categoria de un coach")
    @PatchMapping("/updateCategory")
    @PreAuthorize("hasRole('Admin', 'Coach')")
    public ResponseEntity<Object> updateCoachCategory(@RequestBody CoachCategryDTO coachCategryDTO) {
        try {
            Coach c = categoryService.updateCategoryCoach(coachCategryDTO.getUserDni(),coachCategryDTO.getCategoryName());
            return ResponseEntity.ok(c);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}