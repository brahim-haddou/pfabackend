package com.example.pfabackend.controller;

import com.example.pfabackend.model.Module;
import com.example.pfabackend.model.Salle;
import com.example.pfabackend.service.ModuleService;
import com.example.pfabackend.service.SalleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/salle")
@AllArgsConstructor
public class SalleController {

    private final SalleService salleService;

    @PostMapping
    public ResponseEntity<String> createSallee(@RequestBody Salle salle) {
        salleService.saveSalle(salle);
        return status(HttpStatus.CREATED).body("Salle CREATED Successful");
    }

    @GetMapping
    public ResponseEntity<List<Salle>> getAllSalles() {
        return status(HttpStatus.OK).body(salleService.getAllSalles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salle> getSalle(@PathVariable Long id) {
        return status(HttpStatus.OK).body(salleService.getSalle(id));
    }

    @PutMapping()
    public ResponseEntity<Salle> updateSalle(@RequestBody Salle salle) {
        return status(HttpStatus.OK).body(salleService.updateSalle(salle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSalle(@PathVariable Long id) {
        salleService.deleteSalle(id);
        return status(HttpStatus.OK).body("Salle Deleted Successful");
    }
}
