package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.EmployeeDTO;
import com.APIclubApp.clubApp.dto.FixtureDTO;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Fixture;
import com.APIclubApp.clubApp.service.FixtureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/fixtures")
public class FixtureController {

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper; // Necesitarás una instancia de ModelMapper para convertir entre EmployeeDTO y Employee

    @GetMapping("/list")
    public ResponseEntity<List<Fixture>> listAllFixtures() {
        return ResponseEntity.ok(fixtureService.listAllFixtures());
        /*List<FixtureDTO> fixtureDTOS = fixtureService.listAllFixtures();
        return ResponseEntity.ok(fixtureDTOS);*/
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Fixture> getFixtureById(@PathVariable Long id){

        Fixture fixture = fixtureService.getFixtureById(id);
        if (fixture != null) {
            return ResponseEntity.ok(fixture);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<FixtureDTO> saveFixture(@RequestBody FixtureDTO fixtureDTO) {
        //return ResponseEntity.ok(fixtureService.saveFixture(fixture));
        FixtureDTO savedFixtureDTO = fixtureService.saveFixture(fixtureDTO);
        return ResponseEntity.ok(savedFixtureDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<FixtureDTO> updateFixture(@RequestBody FixtureDTO fixtureDTO) {
        /*ResponseEntity<Fixture> response;
        if (fixture.getFixtureId() != null && fixtureService.getFixtureById(fixture.getFixtureId())) != null){
            response = ResponseEntity.ok(fixtureService.saveFixture(fixture));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;*/

        FixtureDTO updatedFixtureDTO = fixtureService.updateFixture(fixtureDTO);
        if (updatedFixtureDTO != null) {
            return ResponseEntity.ok(updatedFixtureDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFixture(@PathVariable Long id) {
        fixtureService.deleteFixture(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
