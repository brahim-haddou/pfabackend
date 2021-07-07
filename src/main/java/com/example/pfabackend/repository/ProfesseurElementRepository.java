package com.example.pfabackend.repository;

import com.example.pfabackend.model.ProfesseurElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesseurElementRepository extends JpaRepository<ProfesseurElement, Long> {

    ProfesseurElement getByElementIdAndProfesseurId(Long eid, Long pid);

    @Modifying
    @Query("delete from ProfesseurElement pf where pf.element.id = ?1 and pf.professeur.id = ?2")
    void deleteByElementIdAndProfesseurId(Long eid, Long pid);
}
