package com.example.pfabackend.repository;

import com.example.pfabackend.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {

    List<Professeur> findAllByProfesseurElementsElementId(Long id);
}
