package com.example.pfabackend.service;

import com.example.pfabackend.exceptions.ProfesseurNotFoundException;
import com.example.pfabackend.model.Element;
import com.example.pfabackend.repository.ElementRepository;
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

    public void saveElement(Element element){
        elementRepository.save(element);
    }
    public Element updateElement(Element element){
        Element elem = elementRepository.findById(element.getId())
                .orElseThrow(() -> new ProfesseurNotFoundException( "Element With Id "+ element.getId() +" Not Found"));

        elem.setNom(element.getNom());
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
}
