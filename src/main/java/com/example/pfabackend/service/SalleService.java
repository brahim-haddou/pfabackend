package com.example.pfabackend.service;

import com.example.pfabackend.exceptions.ProfesseurNotFoundException;
import com.example.pfabackend.model.Salle;
import com.example.pfabackend.repository.SalleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class SalleService {
    private final SalleRepository salleRepository;

    public void saveSalle(Salle salle){
        salleRepository.save(salle);
    }
    public Salle updateSalle(Salle salle){
        Salle sal = salleRepository.findById(salle.getId())
                .orElseThrow(() -> new ProfesseurNotFoundException( "Salle With Id "+ salle.getId() +" Not Found"));

        sal.setNom(salle.getNom());
        sal.setType(salle.getType());
        sal.setMaxPlace(salle.getMaxPlace());
        return salleRepository.save(sal);
    }
    @Transactional(readOnly = true)
    public Salle getSalle(Long id) {
        return salleRepository.findById(id)
                .orElseThrow(() -> new ProfesseurNotFoundException( "Salle With Id "+ id +" Not Found"));
    }
    @Transactional(readOnly = true)
    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }
    public void deleteSalle(Long id) {
        salleRepository.deleteById(id);
    }
}
