package com.example.pfabackend.repository;

import com.example.pfabackend.model.Classe;
import com.example.pfabackend.model.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
    List<Classe> findAllByElementId(Long id);
}
