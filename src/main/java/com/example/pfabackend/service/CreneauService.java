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


    @Transactional(readOnly = true)
    public Creneau getCreneau(Long id) {
        return creneauRepository.findById(id).orElseThrow(() -> new ProfesseurNotFoundException( "Creneau With Id "+ id +" Not Found"));
    }
    @Transactional(readOnly = true)
    public List<Creneau> getAllCreneau() {
        return creneauRepository.findAllByOrderByIdAsc();
    }
    public List<Creneau> saveCreneauAll(List<Creneau> creneau) {
        return creneauRepository.saveAll(creneau);
    }
    public void deleteAllCreneau() {
        creneauRepository.deleteAll();
    }
}
