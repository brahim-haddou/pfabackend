package com.example.pfabackend.service;

import com.example.pfabackend.exceptions.ProfesseurNotFoundException;
import com.example.pfabackend.model.Creneau;
import com.example.pfabackend.repository.CreneauRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CreneauService {
    private final CreneauRepository creneauRepository;

    public Creneau saveCreneau(Creneau creneau){
        return creneauRepository.save(creneau);
    }
    public Creneau updateCreneau(Creneau creneau){
        Creneau cre = creneauRepository.findById(creneau.getId())
                .orElseThrow(() -> new ProfesseurNotFoundException( "Creneau With Id "+ creneau.getId() +" Not Found"));

        cre.setJour(creneau.getJour());
        cre.setDebut(creneau.getDebut());
        cre.setFin(creneau.getFin());
        return creneauRepository.save(cre);
    }
    @Transactional(readOnly = true)
    public Creneau getCreneau(Long id) {
        return creneauRepository.findById(id)
                .orElseThrow(() -> new ProfesseurNotFoundException( "Creneau With Id "+ id +" Not Found"));
    }
    @Transactional(readOnly = true)
    public List<Creneau> getAllCreneau() {
        return creneauRepository.findAll();
    }
    public void deleteCreneau(Long id) {
        creneauRepository.deleteById(id);
    }

    public void deleteAllCreneau() {
        creneauRepository.deleteAll();
    }
}
