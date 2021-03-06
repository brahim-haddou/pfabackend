package com.example.pfabackend.service;

import com.example.pfabackend.exceptions.ProfesseurNotFoundException;
import com.example.pfabackend.model.Classe;
import com.example.pfabackend.model.EmploiDuTemps;
import com.example.pfabackend.repository.ClasseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ClasseService {

    private final ClasseRepository classeRepository;

    public Classe saveClasse(Classe classe){
        return classeRepository.save(classe);
    }
    public Classe updateClasse(Classe classe){
        Classe cla = classeRepository.findById(classe.getId())
                .orElseThrow(() -> new ProfesseurNotFoundException( "Classe With Id "+ classe.getId() +" Not Found"));
        cla.setNom(classe.getNom());
        cla.setType(classe.getType());
        cla.setMaxEtudiant(classe.getMaxEtudiant());
        cla.setElement(classe.getElement());
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
        List<Classe> li = new ArrayList<>();
        Classe e = classeRepository.getOne(id);
        li.add(e);
        classeRepository.deleteInBatch(li);
    }
}
