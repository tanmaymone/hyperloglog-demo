package com.tanmay.redis.hyperloglog_demo.controller;

import com.tanmay.redis.hyperloglog_demo.service.SimulationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/simulate")
public class SimulationController {

    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping("/{count}")
    public ResponseEntity<String> simulate(@PathVariable int count) {
        simulationService.simulateVisits(count);
        return ResponseEntity.ok("Simulated " + count + " visits");
    }
}
