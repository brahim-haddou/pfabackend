package com.example.pfabackend.controller;

import com.example.pfabackend.dto.CreneauRequest;
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
    public ResponseEntity<List<Creneau>> createCreneau(@RequestBody List<CreneauRequest> creneauRequest) {
        return status(HttpStatus.CREATED).body(creneauService.saveCreneauAll(creneauRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Creneau>> getCreneau(@PathVariable Long id) {
        return status(HttpStatus.CREATED).body(creneauService.getAllCreneau(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCreneau(@PathVariable Long id) {
        creneauService.deleteAllCreneau(id);
        return status(HttpStatus.CREATED).body("All Creneaus Deleted Successful");
    }

}
