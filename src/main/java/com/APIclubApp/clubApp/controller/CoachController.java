package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.CoachBasicDTO;
import com.APIclubApp.clubApp.dto.CoachCategoryDTO;
import com.APIclubApp.clubApp.dto.CoachCategryDTO;
import com.APIclubApp.clubApp.dto.CoachDTO;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.service.CategoryService;
import com.APIclubApp.clubApp.service.CoachService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<List<CoachDTO>> listAllCoaches(){
        return ResponseEntity.ok(coachService.listAllCoaches());
    }

    @Operation(summary = "Obtener todos los coach/dts traer solo nombre e id")
    @GetMapping("/list_basic")
    public ResponseEntity<List<CoachBasicDTO>> listAllCoachesBasic(){
        return ResponseEntity.ok(coachService.listAllCoachesBasic());
}


    @Operation(summary = "Obtener un coach/dt por su ID")
    @GetMapping("/get/{id}")
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

    @Operation(summary = "Crear un coach")
    @PostMapping("/save")
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
    @PermitAll
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
    public ResponseEntity<Object> updateCoachCategory(@RequestBody CoachCategryDTO coachCategryDTO) {
        try {
            Coach c = categoryService.updateCategoryCoach(coachCategryDTO.getUserDni(),coachCategryDTO.getCategoryName());
            return ResponseEntity.ok(c);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Obtiene un coach por su DNI")
    @GetMapping("/getByDni/{dni}")
    @PermitAll
    public ResponseEntity<?> getCoachById(@PathVariable String dni){
        try {
            Coach coach = coachService.getCoachByDNI(dni);
            return ResponseEntity.ok(coach);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Obtiene un coach por su DNI y la categoria asociada")
    @GetMapping("/getByDniCat/{dni}")
    @PermitAll
    public ResponseEntity<?> getCoachByDniCat(@PathVariable String dni) {
        /*try {
            Optional<Coach> coach = coachService.getCoachByDNICat(dni);
            return ResponseEntity.ok(coach);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }*/

        try {
            Optional<Coach> coach = coachService.getCoachByDNICat(dni);
            if (coach.isPresent()) {
                // Obtener el objeto Coach
                Coach coachObj = coach.get();

                // Crear un mapa para almacenar la información del coach y su categoría
                Map<String, Object> response = new HashMap<>();
                response.put("coach", coachObj);
                response.put("category", coachObj.getCategory().getCategoryId()); // Agregar la categoría al mapa

                return ResponseEntity.ok(response); // Devolver el mapa con el coach y la categoría
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coach not found with DNI: " + dni);
            }
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

}