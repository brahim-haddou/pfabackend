package com.example.pfabackend.service;

import com.example.pfabackend.exceptions.ProfesseurNotFoundException;
import com.example.pfabackend.model.Element;
import com.example.pfabackend.model.Module;
import com.example.pfabackend.repository.ElementRepository;
import com.example.pfabackend.repository.ModuleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final ElementRepository elementRepository;

    public void saveModule(Module module){
        moduleRepository.save(module);
    }
    public Module updateModule(Module module){
        Module mod = moduleRepository.findById(module.getId())
                .orElseThrow(() -> new ProfesseurNotFoundException( "Module With Id "+ module.getId() +" Not Found"));

        mod.setNom(module.getNom());
        mod.setFiliere(module.getFiliere());
        return moduleRepository.save(mod);
    }
    @Transactional(readOnly = true)
    public Module getModule(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new ProfesseurNotFoundException( "Module With Id "+ id +" Not Found"));
    }

    @Transactional(readOnly = true)
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }
    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }

    public List<Module> getModulesByFiliereId(Long id) {
        return moduleRepository.findAllByFiliereId(id);
    }

    public List<Element> getModuleElements(Long id) {
        return elementRepository.findAllByModuleId(id);
    }
}
