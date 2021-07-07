package com.example.pfabackend.repository;

import com.example.pfabackend.model.EmploiDuTemps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmploiDuTempsRepository extends JpaRepository<EmploiDuTemps, Long> {
    EmploiDuTemps findByClasseId(Long id);
    List<EmploiDuTemps> findAllByClasseElementModuleFiliereId(Long id);
    List<EmploiDuTemps> findAllBySalleId(Long id);
    boolean existsByClasseId(Long id);
    boolean existsByProfesseurIdAndCreneauId(Long pid,Long cid);
    boolean existsBySalleIdAndCreneauId(Long sid,Long cid);

    @Modifying
    @Query("delete from EmploiDuTemps e where e.classe.element.module.filiere.id = ?1")
    void deleteAllByClasseElementModuleFiliereId(Long id);
}
