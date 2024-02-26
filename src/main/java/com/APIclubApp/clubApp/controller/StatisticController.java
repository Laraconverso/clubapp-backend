package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.model.Statistic;
import com.APIclubApp.clubApp.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/list")
    public ResponseEntity<List<Statistic>> listAllStatistics() {
        return ResponseEntity.ok(statisticService.listAllStatistics());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Statistic> getStatisticById(@PathVariable Long id) {
        ResponseEntity<Statistic> response;

        Statistic statistic = statisticService.getStatisticById(id);
        if (statistic != null) {
            response = ResponseEntity.ok(statistic);
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PostMapping("/save")
    public ResponseEntity<Statistic> saveStatistic(@RequestBody Statistic statistic) {
        return ResponseEntity.ok(statisticService.saveStatistic(statistic));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Statistic> updateStatistic(@PathVariable Long id, @RequestBody Statistic statistic) {
        ResponseEntity<Statistic> response;

        if (statisticService.getStatisticById(id) != null) {
            statistic.setId(id);
            response = ResponseEntity.ok(statisticService.saveStatistic(statistic));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStatistic(@PathVariable Long id) {
        statisticService.deleteStatistic(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
