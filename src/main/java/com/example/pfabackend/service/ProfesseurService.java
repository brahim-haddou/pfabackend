package com.example.pfabackend.service;

import com.example.pfabackend.exceptions.ProfesseurNotFoundException;
import com.example.pfabackend.model.Element;
import com.example.pfabackend.model.Professeur;
import com.example.pfabackend.repository.ElementRepository;
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
public class ProfesseurService {

    private final ProfesseurRepository professeurRepository;
    private final ElementRepository elementRepository;

    public void save(Professeur professeur){
        professeurRepository.save(professeur);
    }
    public Professeur updateProfesseur(Professeur professeur){
        Professeur prof = professeurRepository.findById(professeur.getId())
                .orElseThrow(() -> new ProfesseurNotFoundException( "Professeur With Id "+ professeur.getId() +" Not Found"));

        prof.setNom(professeur.getNom());
        return professeurRepository.save(prof);
    }
    @Transactional(readOnly = true)
    public Professeur getProfesseur(Long id) {
        return professeurRepository.findById(id)
                .orElseThrow(() -> new ProfesseurNotFoundException(id.toString()));
    }

    @Transactional(readOnly = true)
    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll();
    }

    public void deleteProfesseur(Long id) {
        professeurRepository.deleteById(id);
    }

    public List<Element> getProfesseurElements(Long id) {
        return  elementRepository.findAllByProfesseurElementsProfesseurId(id);
    }
}
