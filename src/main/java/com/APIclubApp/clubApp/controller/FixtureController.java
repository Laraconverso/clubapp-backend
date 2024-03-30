package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.FixtureDTO;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.Fixture;
import com.APIclubApp.clubApp.service.FixtureService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
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


    @Operation(summary = "Listar todos los fixtures")
    @GetMapping("/list")
    @PermitAll
    public ResponseEntity<List<Fixture>> listAllFixtures() {
        List<Fixture> fixtures= fixtureService.listAllFixtures();
        if (fixtures.isEmpty()){
            System.out.println("Aún no hay fixtures cargados en la base de datos.");
        }
        return ResponseEntity.ok(fixtureService.listAllFixtures());
        /*List<FixtureDTO> fixtureDTOS = fixtureService.listAllFixtures();
        return ResponseEntity.ok(fixtureDTOS);*/
    }

    @Operation(summary = "Obtener un fixture por ID")
    @GetMapping("/get/{id}")
    @PermitAll
    public ResponseEntity<?> getFixtureById(@PathVariable Long id){
        Fixture fixture = fixtureService.getFixtureById(id);
        try {

            return ResponseEntity.ok(fixture);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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

    @Operation(summary = "Obtener un fixture ID y Nombre")
    @GetMapping("/listIdName")
    @PermitAll
    public ResponseEntity<?> listAllFixtureIdAndName() {
        List<Object[]> fixtureIdAndNameList = fixtureService.listAllFixtureIdAndName();
        try {
            return ResponseEntity.ok(fixtureIdAndNameList);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Crear un fixture")
    @PostMapping("/save")
    @PreAuthorize("hasRole('Admin')")
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

    @Operation(summary = "Actualizar un fixture")
    @PutMapping("/update")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> updateFixture(@RequestBody FixtureDTO fixtureDTO) {
        try {
            Fixture updatedFixture = fixtureService.updateFixture(fixtureDTO);
            return ResponseEntity.ok(updatedFixture);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un fixture por ID")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deleteFixture(@PathVariable Long id) {
        try {
            fixtureService.deleteFixture(id);
            return ResponseEntity.ok().body("Fixture deleted successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
