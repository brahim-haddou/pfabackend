package com.example.pfabackend.repository;

import com.example.pfabackend.model.EmploiDuTemps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EmploiDuTempsRepository extends JpaRepository<EmploiDuTemps, Long> {
    Set<EmploiDuTemps> findByClasseId(Long id);
}
