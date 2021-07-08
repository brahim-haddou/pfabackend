package com.example.pfabackend.controller;

import com.example.pfabackend.model.Salle;
import com.example.pfabackend.service.SalleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/auth/salle")
@AllArgsConstructor
public class SalleController {

    private final SalleService salleService;

    @PostMapping
    public ResponseEntity<Salle> createSallee(@RequestBody Salle salle) {

        return status(HttpStatus.CREATED).body(salleService.saveSalle(salle));
    }

    @GetMapping
    public ResponseEntity<List<Salle>> getAllSalles() {
        return status(HttpStatus.OK).body(salleService.getAllSalles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salle> getSalle(@PathVariable Long id) {
        return status(HttpStatus.OK).body(salleService.getSalle(id));
    }
    @GetMapping("/{type}/{place}")
    public ResponseEntity<List<Salle>> getSalle(@PathVariable String type,@PathVariable int place) {
        return status(HttpStatus.OK).body(salleService.getSalleByPlaceAndType(type, place));
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
