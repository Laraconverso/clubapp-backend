package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.model.Fixture;
import com.APIclubApp.clubApp.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/fixtures")
public class FixtureController {

    @Autowired
    private FixtureService fixtureService;

    @GetMapping("/list")
    public ResponseEntity<List<Fixture>> listAllFixtures() {
        return ResponseEntity.ok(fixtureService.listAllFixtures());
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
    public ResponseEntity<Fixture> saveFixture(@RequestBody Fixture fixture) {
        return ResponseEntity.ok(fixtureService.saveFixture(fixture));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Fixture> updateFixture(@PathVariable Long id, @RequestBody Fixture fixture) {
        ResponseEntity<Fixture> response;
        if (fixtureService.getFixtureById(id) != null) {
            fixture.setFixtureId(id);
            response = ResponseEntity.ok(fixtureService.saveFixture(fixture));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFixture(@PathVariable Long id) {
        fixtureService.deleteFixture(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
