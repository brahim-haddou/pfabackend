package com.example.pfabackend.controller;

import com.example.pfabackend.dto.ClasseRequest;
import com.example.pfabackend.mapper.ClasseMapper;
import com.example.pfabackend.model.Classe;
import com.example.pfabackend.repository.EmploiDuTempsRepository;
import com.example.pfabackend.service.ClasseService;
import com.example.pfabackend.service.ElementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/auth/classe")
@AllArgsConstructor
public class ClasseController {
    private final ClasseService classeService;
    private final ElementService elementService;
    private final EmploiDuTempsRepository emploiDuTempsRepository;
    private final ClasseMapper classeMapper;

    @PostMapping
    public ResponseEntity<String> createClasse(@RequestBody ClasseRequest classeRequest) {
        classeService.saveClasse(
                classeMapper.toclasse(
                        classeRequest,
                       elementService.getElement(classeRequest.getElement_id())
                ));
        return status(HttpStatus.CREATED).body("Classe CREATED Successful");
    }

    @GetMapping
    public ResponseEntity<List<Classe>> getAllClasses() {
        return status(HttpStatus.OK).body(classeService.getAllClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classe> getClasse(@PathVariable Long id) {
        return status(HttpStatus.OK).body(classeService.getClasse(id));
    }

    @PutMapping()
    public ResponseEntity<Classe> updateClasse(@RequestBody ClasseRequest classeRequest) {
        Classe classe = classeMapper.toclasse(classeRequest,elementService.getElement(classeRequest.getElement_id()));
        return status(HttpStatus.OK).body(classeService.updateClasse(classe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClasse(@PathVariable Long id) {
        classeService.deleteClasse(id);
        return status(HttpStatus.OK).body("Classe Deleted Successful");
    }
}
