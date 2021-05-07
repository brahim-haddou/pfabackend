package com.example.pfabackend.controller;

import com.example.pfabackend.model.Module;
import com.example.pfabackend.service.ModuleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/auth/module")
@AllArgsConstructor
public class ModuleController {
    private final ModuleService moduleService;

    @PostMapping
    public ResponseEntity<String> createModule(@RequestBody Module module) {
        moduleService.saveModule(module);
        return status(HttpStatus.CREATED).body("Module CREATED Successful");
    }

    @GetMapping
    public ResponseEntity<List<Module>> getAllModules() {
        return status(HttpStatus.OK).body(moduleService.getAllModules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Module> getModule(@PathVariable Long id) {
        return status(HttpStatus.OK).body(moduleService.getModule(id));
    }

    @PutMapping()
    public ResponseEntity<Module> updateModule(@RequestBody Module module) {
        return status(HttpStatus.OK).body(moduleService.updateModule(module));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return status(HttpStatus.OK).body("Module Deleted Successful");
    }
}
