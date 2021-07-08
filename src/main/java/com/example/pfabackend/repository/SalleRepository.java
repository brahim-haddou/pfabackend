package com.example.pfabackend.repository;

import com.example.pfabackend.model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {
    List<Salle> findAllByTypeAndMaxPlaceGreaterThanEqual(String type, int place);
}
