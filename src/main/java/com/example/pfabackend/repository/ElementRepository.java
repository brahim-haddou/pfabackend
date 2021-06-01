package com.example.pfabackend.repository;

import com.example.pfabackend.model.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementRepository extends JpaRepository<Element, Long>, CrudRepository<Element, Long> {
    List<Element> findAllByModuleId(Long id);
    List<Element> findAllByProfesseurElementsProfesseurId(Long id);
}
