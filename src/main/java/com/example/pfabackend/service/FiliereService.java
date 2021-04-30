package com.example.pfabackend.service;

import com.example.pfabackend.exceptions.ProfesseurNotFoundException;
import com.example.pfabackend.model.Filiere;
import com.example.pfabackend.model.Professeur;
import com.example.pfabackend.repository.FiliereRepository;
import com.example.pfabackend.repository.ProfesseurRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class FiliereService {
    private final FiliereRepository filiereRepository;

    public void saveFiliere(Filiere filiere){
        filiereRepository.save(filiere);
    }
    public Filiere updateFiliere(Filiere filiere){
        Filiere fil = filiereRepository.findById(filiere.getId())
                .orElseThrow(() -> new ProfesseurNotFoundException( "Filiere With Id "+ filiere.getId() +" Not Found"));

        fil.setNom(filiere.getNom());
        return filiereRepository.save(fil);
    }
    @Transactional(readOnly = true)
    public Filiere getFiliere(Long id) {
        return filiereRepository.findById(id)
                .orElseThrow(() -> new ProfesseurNotFoundException( "Filiere With Id "+ id +" Not Found"));
    }
    @Transactional(readOnly = true)
    public List<Filiere> getAllFilieres() {
        return filiereRepository.findAll();
    }
    public void deleteFiliere(Long id) {
        filiereRepository.deleteById(id);
    }
}