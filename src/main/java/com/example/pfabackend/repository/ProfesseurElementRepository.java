package com.example.pfabackend.repository;

import com.example.pfabackend.model.ProfesseurElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesseurElementRepository extends JpaRepository<ProfesseurElement, Long> {

    ProfesseurElement getByElementIdAndProfesseurId(Long eid, Long pid);
    void deleteByElementIdAndProfesseurId(Long eid, Long pid);
}
