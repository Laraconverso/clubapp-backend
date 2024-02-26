package com.APIclubApp.clubApp.controller;


import com.APIclubApp.clubApp.model.Club;
import com.APIclubApp.clubApp.service.ClubService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/clubes")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @Operation(summary = "Guardar un club")
    @PostMapping("/save")

    public ResponseEntity<Club> saveClub(@RequestBody Club club){
        return ResponseEntity.ok(clubService.saveClub(club));
    }

    @Operation(summary = "Listar todos los clubes")
    @GetMapping("/list")

    public ResponseEntity<List<Club>> listAllClubes(){
        return ResponseEntity.ok(clubService.listAllClubes());
    }

    @Operation(summary = "Obtener un club por su ID")
    @GetMapping("/get/{id}")

    public ResponseEntity<Club> getClubById(@PathVariable Long id){
        ResponseEntity<Club> response;

        if (clubService.getClubById(Long.valueOf(id))!=null){
            response = ResponseEntity.ok(clubService.getClubById(Long.valueOf(id))) ;
        }else
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }


}
