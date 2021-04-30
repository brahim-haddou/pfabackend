package com.example.pfabackend.service;

import com.example.pfabackend.exceptions.ProfesseurNotFoundException;
import com.example.pfabackend.model.Classe;
import com.example.pfabackend.model.Filiere;
import com.example.pfabackend.repository.ClasseRepository;
import com.example.pfabackend.repository.FiliereRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ClasseService {

    private final ClasseRepository classeRepository;

    public void saveClasse(Classe classe){
        classeRepository.save(classe);
    }
    public Classe updateClasse(Classe classe){
        Classe cla = classeRepository.findById(classe.getId())
                .orElseThrow(() -> new ProfesseurNotFoundException( "Classe With Id "+ classe.getId() +" Not Found"));

        cla.setNom(classe.getNom());
        cla.setType(classe.getType());
        cla.setMaxEtudiant(classe.getMaxEtudiant());
        return classeRepository.save(cla);
    }
    @Transactional(readOnly = true)
    public Classe getClasse(Long id) {
        return classeRepository.findById(id)
                .orElseThrow(() -> new ProfesseurNotFoundException( "Classe With Id "+ id +" Not Found"));
    }
    @Transactional(readOnly = true)
    public List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }
    public void deleteClasse(Long id) {
        classeRepository.deleteById(id);
    }
}
