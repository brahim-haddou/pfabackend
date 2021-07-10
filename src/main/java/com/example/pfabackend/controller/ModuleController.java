package com.example.pfabackend.controller;

import com.example.pfabackend.dto.ModuleRequest;
import com.example.pfabackend.mapper.ModuleMapper;
import com.example.pfabackend.model.Element;
import com.example.pfabackend.model.Module;
import com.example.pfabackend.service.FiliereService;
import com.example.pfabackend.service.ModuleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/module")
@AllArgsConstructor
public class ModuleController {
    private final FiliereService filiereService;
    private final ModuleService moduleService;
    private final ModuleMapper moduleMapper;

    @PostMapping
    public ResponseEntity<Module> createModule(@RequestBody ModuleRequest moduleRequest) {
        return status(HttpStatus.CREATED).body(moduleService.saveModule(
                moduleMapper.toModule(
                        moduleRequest,
                        filiereService.getFiliere(moduleRequest.getFiliereId())
                )
        ));
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
    public ResponseEntity<Module> updateModule(@RequestBody ModuleRequest moduleRequest) {
        Module module = moduleMapper.toModule(
                moduleRequest,
                filiereService.getFiliere(moduleRequest.getFiliereId()
                )
        );
        return status(HttpStatus.OK).body(moduleService.updateModule(module));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return status(HttpStatus.OK).body("Module Deleted Successful");
    }

    @GetMapping("/{id}/elements")
    public ResponseEntity<List<Element>> getModuleElements(@PathVariable Long id) {
        return status(HttpStatus.OK).body(moduleService.getModuleElements(id));
    }
}
