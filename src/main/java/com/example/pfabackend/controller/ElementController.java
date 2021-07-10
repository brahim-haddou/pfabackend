package com.example.pfabackend.controller;

import com.example.pfabackend.dto.ElementRequest;
import com.example.pfabackend.mapper.ElementMapper;
import com.example.pfabackend.model.Classe;
import com.example.pfabackend.model.Element;
import com.example.pfabackend.model.Professeur;
import com.example.pfabackend.service.ElementService;
import com.example.pfabackend.service.ModuleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/element")
@AllArgsConstructor
public class ElementController {
    private final ElementService elementService;
    private final ElementMapper elementMapper;
    private final ModuleService moduleService;

    @PostMapping
    public ResponseEntity<Element> createElement(@RequestBody ElementRequest elementRequest) {
        return status(HttpStatus.CREATED).body(elementService.saveElement(
                elementMapper.toElement(elementRequest,
                moduleService.getModule(elementRequest.getModuleId())
                )
            )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Element> getElement(@PathVariable Long id) {
        return status(HttpStatus.OK).body(elementService.getElement(id));
    }

    @GetMapping
    public ResponseEntity<List<Element>> getElement() {
        return status(HttpStatus.OK).body(elementService.getAllElements());
    }
    @PutMapping()
    public ResponseEntity<Element> updateElement(@RequestBody ElementRequest elementRequest) {
        Element element = elementMapper.toElement(elementRequest,
                moduleService.getModule(elementRequest.getModuleId())
        );
        return status(HttpStatus.OK).body(elementService.updateElement(element));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteElement(@PathVariable Long id) {
        elementService.deleteElement(id);
        return status(HttpStatus.OK).body("Element Deleted Successful");
    }

    @GetMapping("/{id}/classes")
    public ResponseEntity<List<Classe>> getAllElements(@PathVariable Long id) {
        return status(HttpStatus.OK).body(elementService.getElementClasses(id));
    }

    @GetMapping("/{id}/professeurs")
    public ResponseEntity<List<Professeur>> getAllProfesseurs(@PathVariable Long id) {
        return status(HttpStatus.OK).body(elementService.getElementProfesseurs(id));
    }

    @PostMapping("/{eid}/professeurs/{pid}")
    public ResponseEntity<String> addProfesseurToElement(@PathVariable Long eid, @PathVariable Long pid) {
        return status(HttpStatus.OK).body(elementService.addProfesseurToElement(eid,pid));
    }
    @DeleteMapping("/{eid}/professeurs/{pid}")
    public ResponseEntity<String> deleteProfesseurFromElement(@PathVariable Long eid, @PathVariable Long pid) {
        return status(HttpStatus.OK).body(elementService.deleteProfesseurFromElement(eid, pid));
    }
}
