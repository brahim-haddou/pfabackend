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
    public ResponseEntity<List<Creneau>> createCreneau(@RequestBody List<Creneau> creneau) {
        return status(HttpStatus.CREATED).body(creneauService.saveCreneauAll(creneau));
    }
    @GetMapping
    public ResponseEntity<List<Creneau>> getCreneau() {
        return status(HttpStatus.CREATED).body(creneauService.getAllCreneau());
    }
    @DeleteMapping
    public ResponseEntity<String> deleteCreneau() {
        creneauService.deleteAllCreneau();
        return status(HttpStatus.CREATED).body("All Creneaus Deleted Successful");
    }

}
