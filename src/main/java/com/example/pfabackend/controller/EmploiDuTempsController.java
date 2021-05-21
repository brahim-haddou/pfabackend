package com.example.pfabackend.controller;

import com.example.pfabackend.dto.EmploiDuTempsRequest;
import com.example.pfabackend.model.EmploiDuTemps;
import com.example.pfabackend.model.ExcelGenerator;
import com.example.pfabackend.repository.CreneauRepository;
import com.example.pfabackend.service.EmploiDuTempsService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/auth/emploiDuTemps")
@AllArgsConstructor
public class EmploiDuTempsController {

    private final EmploiDuTempsService emploiDuTempsService;
    private final CreneauRepository creaRepository;
    @GetMapping("/filiere/{id}")
    public ResponseEntity<List<EmploiDuTemps>> getFiliereEmploiDuTemps(@PathVariable Long id) {
        return status(HttpStatus.OK).body(emploiDuTempsService.getFiliereEmploiDuTemps(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmploiDuTemps> getEmploiDuTemps(@PathVariable Long id) {
        return status(HttpStatus.OK).body(emploiDuTempsService.getEmploiDuTemps(id));
    }
    @PostMapping()
    public ResponseEntity<EmploiDuTemps> saveEmploiDuTemps(@RequestBody EmploiDuTempsRequest emploiDuTempsRequest){
        return status(HttpStatus.OK).body(emploiDuTempsService.save(emploiDuTempsRequest));
    }
    @PutMapping()
    public ResponseEntity<EmploiDuTemps> updateEmploiDuTemps(@RequestBody EmploiDuTempsRequest emploiDuTempsRequest){
        System.out.println(emploiDuTempsRequest);
        return status(HttpStatus.OK).body(emploiDuTempsService.update(emploiDuTempsRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmploiDuTemps(@PathVariable Long id) {
        emploiDuTempsService.deleteEmploiDuTemps(id);
        return status(HttpStatus.OK).body("EmploiDuTemps Deleted Successful");
    }

    // TODO: add | delete | update for all modules


    @PutMapping("/{id}/classe/{cid}")
    public ResponseEntity<EmploiDuTemps> updateClasseEmploiDuTemps(@PathVariable Long id, @PathVariable Long cid){
        return status(HttpStatus.OK).body(emploiDuTempsService.updateClasseEmploiDuTemps(id,cid));
    }
    @DeleteMapping("/{id}/classe")
    public ResponseEntity<String> deleteClasseFromEmploiDuTemps(@PathVariable Long id) {
        emploiDuTempsService.deleteClasseFromEmploiDuTemps(id);
        return status(HttpStatus.OK).body("EmploiDuTemps Deleted Successful");
    }

    @PutMapping("/{id}/professeur/{pid}")
    public ResponseEntity<EmploiDuTemps> updateProfesseurEmploiDuTemps(@PathVariable Long id, @PathVariable Long pid){
        return status(HttpStatus.OK).body(emploiDuTempsService.updateProfesseurEmploiDuTemps(id,pid));
    }
    @DeleteMapping("/{id}/professeur")
    public ResponseEntity<String> deleteProfesseurFromEmploiDuTemps(@PathVariable Long id) {
        emploiDuTempsService.deleteProfesseurEmploiDuTemps(id);
        return status(HttpStatus.OK).body("EmploiDuTemps Deleted Successful");
    }

    @PutMapping("/{id}/salle/{sid}")
    public ResponseEntity<EmploiDuTemps> updateSalleEmploiDuTemps(@PathVariable Long id, @PathVariable Long sid){
        return status(HttpStatus.OK).body(emploiDuTempsService.updateSalleEmploiDuTemps(id,sid));
    }
    @DeleteMapping("/{id}/salle")
    public ResponseEntity<String> deleteSalleFromEmploiDuTemps(@PathVariable Long id) {
        emploiDuTempsService.deleteSalleEmploiDuTemps(id);
        return status(HttpStatus.OK).body("EmploiDuTemps Deleted Successful");
    }


    // TODO: generate excel file
    @GetMapping(value = "/filiere/{id}/Excel")
    public ResponseEntity<InputStreamResource> excelCustomersReport(@PathVariable Long id) throws IOException {
        ByteArrayInputStream in = emploiDuTempsService.getFiliereEmploiDuTempsExcel(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=emploiDuTempsService.xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

}
