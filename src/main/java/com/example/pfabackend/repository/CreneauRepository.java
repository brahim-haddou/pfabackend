package com.example.pfabackend.repository;

import com.example.pfabackend.model.Creneau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreneauRepository extends JpaRepository<Creneau, Long> {
    List<Creneau> getAllByJourOrderByDebut(String jour);
    List<Creneau> findAllByOrderByIdAsc();
}
