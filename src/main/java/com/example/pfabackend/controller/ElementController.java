package com.example.pfabackend.controller;

import com.example.pfabackend.model.Creneau;
import com.example.pfabackend.model.Element;
import com.example.pfabackend.service.CreneauService;
import com.example.pfabackend.service.ElementService;
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

    @PostMapping
    public ResponseEntity<String> createElement(@RequestBody Element element) {
        elementService.saveElement(element);
        return status(HttpStatus.CREATED).body("Element CREATED Successful");
    }

    @GetMapping
    public ResponseEntity<List<Element>> getAllElements() {
        return status(HttpStatus.OK).body(elementService.getAllElements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Element> getElement(@PathVariable Long id) {
        return status(HttpStatus.OK).body(elementService.getElement(id));
    }

    @PutMapping()
    public ResponseEntity<Element> updateElement(@RequestBody Element element) {
        return status(HttpStatus.OK).body(elementService.updateElement(element));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteElement(@PathVariable Long id) {
        elementService.deleteElement(id);
        return status(HttpStatus.OK).body("Element Deleted Successful");
    }
}
