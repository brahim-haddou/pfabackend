package com.example.pfabackend.controller;

import com.example.pfabackend.model.Filiere;
import com.example.pfabackend.model.Module;
import com.example.pfabackend.service.FiliereService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/auth/filiere")
@AllArgsConstructor
public class FiliereController {
    private final FiliereService filiereService;

    @PostMapping
    public ResponseEntity<String> createFiliere(@RequestBody Filiere filiere) {
        System.out.println(filiere);
        filiereService.saveFiliere(filiere);
        return status(HttpStatus.CREATED).body("Filiere CREATED Successful");
    }

    @GetMapping
    public ResponseEntity<List<Filiere>> getAllFilieres() {
        return status(HttpStatus.OK).body(filiereService.getAllFilieres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filiere> getFiliere(@PathVariable Long id) {
        return status(HttpStatus.OK).body(filiereService.getFiliere(id));
    }
    @PutMapping()
    public ResponseEntity<Filiere> updateFiliere(@RequestBody Filiere filiere) {
        return status(HttpStatus.OK).body(filiereService.updateFiliere(filiere));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFiliere(@PathVariable Long id) {
        filiereService.deleteFiliere(id);
        return status(HttpStatus.OK).body("Filiere Deleted Successful");
    }

    @GetMapping("/{id}/modules")
    public ResponseEntity<List<Module>> getFiliereModules(@PathVariable Long id) {
        return status(HttpStatus.OK).body(filiereService.getFiliereModules(id));
    }
}
