package com.example.pfabackend.repository;

import com.example.pfabackend.model.Creneau;
import com.example.pfabackend.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreneauRepository extends JpaRepository<Creneau, Long> {
    List<Creneau> getAllByJourOrderByDebut(String jour);
    List<Creneau> findAllByFiliereIdOrderByIdAsc(Long id);

    @Modifying
    @Query("delete from Creneau c where c.filiere.id = ?1")
    void removeAllByFiliereId(Long filiereId);
}
