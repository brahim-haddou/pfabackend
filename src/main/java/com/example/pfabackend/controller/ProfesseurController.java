package com.example.pfabackend.controller;

import com.example.pfabackend.model.Element;
import com.example.pfabackend.model.Professeur;
import com.example.pfabackend.service.ProfesseurService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/auth/professeur")
@AllArgsConstructor
public class ProfesseurController {
    private final ProfesseurService professeurService;

    @PostMapping
    public ResponseEntity<Professeur> createProfesseur(@RequestBody Professeur professeur) {
        return status(HttpStatus.CREATED).body(professeurService.save(professeur));
    }

    @GetMapping
    public ResponseEntity<List<Professeur>> getAllProfesseurs() {
        return status(HttpStatus.OK).body(professeurService.getAllProfesseurs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professeur> getProfesseur(@PathVariable Long id) {
        return status(HttpStatus.OK).body(professeurService.getProfesseur(id));
    }

    @PutMapping()
    public ResponseEntity<Professeur> updateProfesseur(@RequestBody Professeur professeur) {
        return status(HttpStatus.OK).body(professeurService.updateProfesseur(professeur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfesseur(@PathVariable Long id) {
        professeurService.deleteProfesseur(id);
        return status(HttpStatus.OK).body("Professeur Deleted Successful");
    }
    @GetMapping("/{id}/elements")
    public ResponseEntity<List<Element>> getProfesseurElements(@PathVariable Long id) {
        return status(HttpStatus.OK).body(professeurService.getProfesseurElements(id));
    }
}
