package com.example.pfabackend.controller;

import com.example.pfabackend.model.Creneau;
import com.example.pfabackend.service.CreneauService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/auth/creneau")
@AllArgsConstructor
public class CreneauController {
    private final CreneauService creneauService;

    @PostMapping
    public ResponseEntity<String> createCreneau(@RequestBody Creneau creneau) {
        creneauService.saveCreneau(creneau);
        return status(HttpStatus.CREATED).body("Creneau CREATED Successful");
    }

    @GetMapping
    public ResponseEntity<List<Creneau>> getAllCreneaus() {
        return status(HttpStatus.OK).body(creneauService.getAllCreneau());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Creneau> getCreneau(@PathVariable Long id) {
        return status(HttpStatus.OK).body(creneauService.getCreneau(id));
    }

    @PutMapping()
    public ResponseEntity<Creneau> updateCreneau(@RequestBody Creneau creneau) {
        return status(HttpStatus.OK).body(creneauService.updateCreneau(creneau));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCreneau(@PathVariable Long id) {
        creneauService.deleteCreneau(id);
        return status(HttpStatus.OK).body("Creneau Deleted Successful");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteCreneau() {
        creneauService.deleteAllCreneau();
        return status(HttpStatus.OK).body("Creneau Deleted Successful");
    }
}
