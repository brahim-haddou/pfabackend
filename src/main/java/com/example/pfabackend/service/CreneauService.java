package com.example.pfabackend.service;

import com.example.pfabackend.dto.CreneauRequest;
import com.example.pfabackend.exceptions.ProfesseurNotFoundException;
import com.example.pfabackend.mapper.CreneauMapper;
import com.example.pfabackend.model.Creneau;
import com.example.pfabackend.repository.CreneauRepository;
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
public class CreneauService {
    private final CreneauRepository creneauRepository;
    private final FiliereService filiereService;
    private final CreneauMapper creneauMapper;

    @Transactional(readOnly = true)
    public Creneau getCreneau(Long id) {
        return creneauRepository.findById(id).orElseThrow(() -> new ProfesseurNotFoundException( "Creneau With Id "+ id +" Not Found"));
    }
    @Transactional(readOnly = true)
    public List<Creneau> getAllCreneau(Long id) {
        return creneauRepository.findAllByFiliereIdOrderByIdAsc(id);
    }
    public List<Creneau> saveCreneauAll(List<CreneauRequest> creneauRequests) {
        List<Creneau> creneau = new ArrayList<>();
        for (CreneauRequest cRequest: creneauRequests) {
            Creneau c = creneauMapper.toCreneau(cRequest,filiereService.getFiliere(cRequest.getFiliereId()));
            creneau.add(c);
        }
        return creneauRepository.saveAll(creneau);
    }
    public void deleteAllCreneau(Long id) {
        creneauRepository.removeAllByFiliereId(id);
    }
}
