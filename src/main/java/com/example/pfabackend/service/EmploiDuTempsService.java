package com.example.pfabackend.service;

import com.example.pfabackend.dto.EmploiDuTempsRequest;
import com.example.pfabackend.exceptions.ProfesseurNotFoundException;
import com.example.pfabackend.exceptions.SpringPfaException;
import com.example.pfabackend.model.*;
import com.example.pfabackend.repository.CreneauRepository;
import com.example.pfabackend.repository.EmploiDuTempsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class EmploiDuTempsService {

    private final EmploiDuTempsRepository emploiDuTempsRepository;
    private final CreneauService creneauService;
    private final ClasseService classeService;
    private final ProfesseurService professeurService;
    private final SalleService salleService;
    private final CreneauRepository creneauRepository;


    public EmploiDuTemps save(EmploiDuTempsRequest emploiDuTempsRequest){
        EmploiDuTemps emploiDuTemps = new EmploiDuTemps();
        if(emploiDuTempsRequest.getClasseId() != null)
        {
            Classe classe = classeService.getClasse(emploiDuTempsRequest.getClasseId());
            emploiDuTemps.setClasse(classe);
        }
        else{ throw new SpringPfaException("EmploiDuTempsRequest Exception"); }
        if(emploiDuTempsRequest.getCreneauId() != null)
        {
            Creneau creneau = creneauService.getCreneau(emploiDuTempsRequest.getCreneauId());
            emploiDuTemps.setCreneau(creneau);
            if(
                    emploiDuTempsRequest.getSalleId() != null
                            &&
                    !emploiDuTempsRepository.existsBySalleIdAndCreneauId(emploiDuTempsRequest.getSalleId(),emploiDuTempsRequest.getCreneauId())
            )
            {
                Salle salle = salleService.getSalle(emploiDuTempsRequest.getSalleId());
                emploiDuTemps.setSalle(salle);
            }
            else{
                throw new SpringPfaException("EmploiDuTempsRequest Exception");
            }
            if(
                    emploiDuTempsRequest.getProfesseurId() != null
                            &&
                    !emploiDuTempsRepository.existsByProfesseurIdAndCreneauId(emploiDuTempsRequest.getProfesseurId(),emploiDuTempsRequest.getCreneauId())
            )
            {
                Professeur professeur = professeurService.getProfesseur(emploiDuTempsRequest.getProfesseurId());
                emploiDuTemps.setProfesseur(professeur);
            }
            else{
                throw new SpringPfaException("EmploiDuTempsRequest Exception");
            }
        }
        else{
            throw new SpringPfaException("EmploiDuTempsRequest Exception");
        }
        return emploiDuTempsRepository.save(emploiDuTemps);
    }

    public EmploiDuTemps update(EmploiDuTempsRequest emploiDuTempsRequest){
        EmploiDuTemps emploiDuTemps = emploiDuTempsRepository.findById(emploiDuTempsRequest.getId())
                .orElseThrow(() -> new ProfesseurNotFoundException( "EmploiDuTemps With Id "+ emploiDuTempsRequest.getId() +" Not Found"));

        if(emploiDuTempsRequest.getClasseId() != null)
        {
            Classe classe = classeService.getClasse(emploiDuTempsRequest.getClasseId());
            emploiDuTemps.setClasse(classe);
        }
        else{ throw new SpringPfaException("EmploiDuTempsRequest Exception"); }
        if(emploiDuTempsRequest.getCreneauId() != null)
        {
            Creneau creneau = creneauService.getCreneau(emploiDuTempsRequest.getCreneauId());
            emploiDuTemps.setCreneau(creneau);
            if(emploiDuTempsRequest.getSalleId() != null)
            {
                Salle salle = salleService.getSalle(emploiDuTempsRequest.getSalleId());
                emploiDuTemps.setSalle(salle);
            }
            else{
                throw new SpringPfaException("EmploiDuTempsRequest Exception");
            }
            if(emploiDuTempsRequest.getProfesseurId() != null)
            {
                Professeur professeur = professeurService.getProfesseur(emploiDuTempsRequest.getProfesseurId());
                emploiDuTemps.setProfesseur(professeur);
            }
            else{
                throw new SpringPfaException("EmploiDuTempsRequest Exception");
            }
        }
        else{
            throw new SpringPfaException("EmploiDuTempsRequest Exception");
        }
        return emploiDuTempsRepository.save(emploiDuTemps);
    }

    public List<EmploiDuTemps> getFiliereEmploiDuTemps(Long id) {
        return emploiDuTempsRepository.findAllByClasseElementModuleFiliereId(id);
    }
    public ByteArrayInputStream getFiliereEmploiDuTempsExcel(Long id) throws IOException {
        List<Creneau> creneau = creneauRepository.getAllByJourOrderByDebut("Lundi");
        List<EmploiDuTemps> emploiTemps = emploiDuTempsRepository.findAllByClasseElementModuleFiliereId(id);

        return ExcelGenerator.customersToExcel(emploiTemps, creneau);
    }

    public List<EmploiDuTemps> getSalleEmploiDuTemps(Long id) {
        return emploiDuTempsRepository.findAllBySalleId(id);
    }
    public ByteArrayInputStream getSalleEmploiDuTempsExcel(Long id) throws IOException {
        List<Creneau> creneau = creneauRepository.getAllByJourOrderByDebut("Lundi");
        List<EmploiDuTemps> emploiTemps = emploiDuTempsRepository.findAllBySalleId(id);

        return ExcelGenerator.customersToExcel(emploiTemps, creneau);
    }
    public ByteArrayInputStream getProfesseurEmploiDuTempsExcel(Long id) throws IOException {
        List<Creneau> creneau = creneauRepository.getAllByJourOrderByDebut("Lundi");
        List<EmploiDuTemps> emploiTemps = emploiDuTempsRepository.findAllByProfesseurId(id);

        return ExcelGenerator.customersToExcel(emploiTemps, creneau);
    }
    public EmploiDuTemps getEmploiDuTemps(Long id) {
        return emploiDuTempsRepository.findById(id)
                .orElseThrow(() -> new ProfesseurNotFoundException( "EmploiDuTemps With Id "+ id +" Not Found"));
    }

    public void deleteEmploiDuTemps(Long id) {
        List<EmploiDuTemps> li = new ArrayList<>();
        EmploiDuTemps e = emploiDuTempsRepository.getOne(id);
        li.add(e);
        emploiDuTempsRepository.deleteInBatch(li);
    }
    public void deleteFiliereEmploiDuTemps(Long id) {
        List<EmploiDuTemps> lis = emploiDuTempsRepository.findAllByClasseElementModuleFiliereId(id);
        emploiDuTempsRepository.deleteInBatch(lis);
    }
    public List<EmploiDuTemps> findAll() {
        return emploiDuTempsRepository.findAll();
    }

    public EmploiDuTemps updateClasseEmploiDuTemps(Long id, Long cid) {
        EmploiDuTemps emploiTemps = emploiDuTempsRepository.findById(id)
                .orElseThrow(() -> new ProfesseurNotFoundException( "EmploiDuTemps With Id "+ id +" Not Found"));
        Classe classe = classeService.getClasse(cid);
        emploiTemps.setClasse(classe);
        return emploiDuTempsRepository.save(emploiTemps);
    }

    public EmploiDuTemps deleteClasseFromEmploiDuTemps(Long id) {
        EmploiDuTemps emploiTemps = emploiDuTempsRepository.findById(id)
                .orElseThrow(() -> new ProfesseurNotFoundException( "EmploiDuTemps With Id "+ id +" Not Found"));
        emploiTemps.setClasse(null);
        return emploiDuTempsRepository.save(emploiTemps);
    }

    public EmploiDuTemps updateProfesseurEmploiDuTemps(Long id, Long pid) {
        EmploiDuTemps emploiTemps = emploiDuTempsRepository.findById(id)
                .orElseThrow(() -> new ProfesseurNotFoundException( "EmploiDuTemps With Id "+ id +" Not Found"));
        Professeur professeur = professeurService.getProfesseur(pid);
        emploiTemps.setProfesseur(professeur);
        return emploiDuTempsRepository.save(emploiTemps);
    }

    public EmploiDuTemps deleteProfesseurEmploiDuTemps(Long id) {
        EmploiDuTemps emploiTemps = emploiDuTempsRepository.findById(id)
                .orElseThrow(() -> new ProfesseurNotFoundException( "EmploiDuTemps With Id "+ id +" Not Found"));
        emploiTemps.setClasse(null);
        return emploiDuTempsRepository.save(emploiTemps);
    }

    public EmploiDuTemps updateSalleEmploiDuTemps(Long id, Long sid) {
        EmploiDuTemps emploiTemps = emploiDuTempsRepository.findById(id)
                .orElseThrow(() -> new ProfesseurNotFoundException( "EmploiDuTemps With Id "+ id +" Not Found"));
        Salle salle = salleService.getSalle(sid);
        emploiTemps.setSalle(salle);
        return emploiDuTempsRepository.save(emploiTemps);
    }

    public EmploiDuTemps deleteSalleEmploiDuTemps(Long id) {
        EmploiDuTemps emploiTemps = emploiDuTempsRepository.findById(id)
                .orElseThrow(() -> new ProfesseurNotFoundException( "EmploiDuTemps With Id "+ id +" Not Found"));
        emploiTemps.setSalle(null);
        return emploiDuTempsRepository.save(emploiTemps);
    }

}
