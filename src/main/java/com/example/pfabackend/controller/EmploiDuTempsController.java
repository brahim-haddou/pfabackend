package com.example.pfabackend.controller;

import com.example.pfabackend.dto.EmploiDuTempsRequest;
import com.example.pfabackend.model.EmploiDuTemps;
import com.example.pfabackend.service.EmploiDuTempsService;
import com.example.pfabackend.service.FiliereService;
import com.example.pfabackend.service.SalleService;
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
    private final FiliereService filiereService;
    private final SalleService salleService;

    @GetMapping
    public ResponseEntity<List<EmploiDuTemps>> getFiliereEmploiDuTemps() {
        return status(HttpStatus.OK).body(emploiDuTempsService.findAll());
    }
    @GetMapping("/salle/{id}")
    public ResponseEntity<List<EmploiDuTemps>> getSalleEmploiDuTemps(@PathVariable Long id) {
        return status(HttpStatus.OK).body(emploiDuTempsService.getSalleEmploiDuTemps(id));
    }

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

    @PutMapping("/{id}/classe/{cid}")
    public ResponseEntity<EmploiDuTemps> updateClasseEmploiDuTemps(@PathVariable Long id, @PathVariable Long cid){
        return status(HttpStatus.OK).body(emploiDuTempsService.updateClasseEmploiDuTemps(id,cid));
    }
    @PutMapping("/{id}/professeur/{pid}")
    public ResponseEntity<EmploiDuTemps> updateProfesseurEmploiDuTemps(@PathVariable Long id, @PathVariable Long pid){
        return status(HttpStatus.OK).body(emploiDuTempsService.updateProfesseurEmploiDuTemps(id,pid));
    }
    @PutMapping("/{id}/salle/{sid}")
    public ResponseEntity<EmploiDuTemps> updateSalleEmploiDuTemps(@PathVariable Long id, @PathVariable Long sid){
        return status(HttpStatus.OK).body(emploiDuTempsService.updateSalleEmploiDuTemps(id,sid));
    }

    @DeleteMapping("/{id}/classe")
    public ResponseEntity<EmploiDuTemps> deleteClasseFromEmploiDuTemps(@PathVariable Long id) {
        return status(HttpStatus.OK).body(emploiDuTempsService.deleteClasseFromEmploiDuTemps(id));
    }
    @DeleteMapping("/{id}/professeur")
    public ResponseEntity<EmploiDuTemps> deleteProfesseurFromEmploiDuTemps(@PathVariable Long id) {
        return status(HttpStatus.OK).body(emploiDuTempsService.deleteProfesseurEmploiDuTemps(id));
    }
    @DeleteMapping("/{id}/salle")
    public ResponseEntity<EmploiDuTemps> deleteSalleFromEmploiDuTemps(@PathVariable Long id) {
        return status(HttpStatus.OK).body(emploiDuTempsService.deleteSalleEmploiDuTemps(id));
    }

    @GetMapping(value = "/filiere/{id}/excel")
    public ResponseEntity<InputStreamResource> excelFiliereEmploiDuTemps(@PathVariable Long id) throws IOException {
        ByteArrayInputStream in = emploiDuTempsService.getFiliereEmploiDuTempsExcel(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename="+filiereService.getFiliere(id).getNom()+"emploiDuTemps.xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }
    @GetMapping(value = "/salle/{id}/excel")
    public ResponseEntity<InputStreamResource> excelSalleEmploiDuTemps(@PathVariable Long id) throws IOException {
        ByteArrayInputStream in = emploiDuTempsService.getSalleEmploiDuTempsExcel(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename="+salleService.getSalle(id).getNom()+"DuTempsService.xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }
}
