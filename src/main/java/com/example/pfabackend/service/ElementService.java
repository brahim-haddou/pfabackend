package com.example.pfabackend.service;

import com.example.pfabackend.exceptions.ProfesseurNotFoundException;
import com.example.pfabackend.model.Classe;
import com.example.pfabackend.model.Element;
import com.example.pfabackend.model.Professeur;
import com.example.pfabackend.model.ProfesseurElement;
import com.example.pfabackend.repository.ClasseRepository;
import com.example.pfabackend.repository.ElementRepository;
import com.example.pfabackend.repository.ProfesseurElementRepository;
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
public class ElementService {
    private final ElementRepository elementRepository;
    private final ClasseRepository classRepository;
    private final ProfesseurRepository professeurRepository;
    private final ProfesseurElementRepository professeurElementRepository;

    public Element saveElement(Element element){
        return elementRepository.save(element);
    }
    public Element updateElement(Element element){
        Element elem = elementRepository.findById(element.getId())
                .orElseThrow(() -> new ProfesseurNotFoundException( "Element With Id "+ element.getId() +" Not Found"));

        elem.setNom(element.getNom());
        elem.setModule(element.getModule());
        return elementRepository.save(elem);
    }
    @Transactional(readOnly = true)
    public Element getElement(Long id) {
        return elementRepository.findById(id)
                .orElseThrow(() -> new ProfesseurNotFoundException( "Element With Id "+ id +" Not Found"));
    }
    @Transactional(readOnly = true)
    public List<Element> getAllElements() {
        return elementRepository.findAll();
    }
    public void deleteElement(Long id) {
        elementRepository.deleteById(id);
    }

    public List<Classe> getElementClasses(Long id) {
        return classRepository.findAllByElementId(id);
    }

    public List<Professeur> getElementProfesseurs(Long id) {
        return  professeurRepository.findAllByProfesseurElementsElementId(id);
    }

    public String addProfesseurToElement(Long eid, Long pid) {
        if(professeurElementRepository.getByElementIdAndProfesseurId(eid,pid) == null){
            professeurElementRepository.saveAndFlush(
                    new ProfesseurElement(null,
                            professeurRepository.findById(pid)
                                    .orElseThrow(() -> new ProfesseurNotFoundException( "Professeur With Id "+ pid +" Not Found")),
                            elementRepository.findById(eid)
                                    .orElseThrow(() -> new ProfesseurNotFoundException( "Element With Id "+ eid +" Not Found"))
                    )
            );
            return "Done";
        }
        return "already existed";
    }

    public String deleteProfesseurFromElement(Long eid, Long pid) {
        professeurElementRepository.deleteByElementIdAndProfesseurId(eid,pid);
        return "Done";
    }
}
