package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.EmployeeDTO;
import com.APIclubApp.clubApp.dto.FixtureDTO;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Fixture;
import com.APIclubApp.clubApp.service.FixtureService;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
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

   // private static final Logger logger = (Logger) LoggerFactory.getLogger(FixtureController.class);


    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper; // instancia de ModelMapper para convertir entre EmployeeDTO y Employee



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

    /*@GetMapping("/getWithGames/{id}")
    public ResponseEntity<Fixture> getFixtureWithGamesById(@PathVariable Long id) {
        Fixture fixture = fixtureService.getFixtureByIdWithGames(id);
        if (fixture != null) {
            return ResponseEntity.ok(fixture);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }*/

    @GetMapping("/listIdName")
    public ResponseEntity<List<Object[]>> listAllFixtureIdAndName() {
        List<Object[]> fixtureIdAndNameList = fixtureService.listAllFixtureIdAndName();
        return ResponseEntity.ok(fixtureIdAndNameList);
    }


    @PostMapping("/save")
    public ResponseEntity<Fixture> saveFixture(@RequestBody FixtureDTO fixtureDTO) {

        // Llamar al método saveFixture del servicio para guardar el Fixture
        Fixture savedFixture = fixtureService.saveFixture(fixtureDTO);

        // Verificar si se pudo guardar el Fixture
        if (savedFixture != null) {
            // Si se guardó correctamente, devolver una respuesta con el Fixture guardado y el estado HTTP 200 OK
            return ResponseEntity.ok(savedFixture);
        } else {
            // Si no se pudo guardar, devolver una respuesta con el estado HTTP 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/update")
    public ResponseEntity<Fixture> updateFixture(@RequestBody FixtureDTO fixtureDTO) {
        ResponseEntity<Fixture> response;
        Fixture updatedFixture = fixtureService.updateFixture(fixtureDTO);
        if (updatedFixture != null){
            response = ResponseEntity.ok(updatedFixture);
        }else{
            response = ResponseEntity.notFound().build();
        }
        return response;

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFixture(@PathVariable Long id) {
        fixtureService.deleteFixture(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
