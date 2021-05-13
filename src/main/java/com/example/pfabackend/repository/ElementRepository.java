package com.example.pfabackend.repository;

import com.example.pfabackend.model.Element;
import com.example.pfabackend.model.Module;
import com.example.pfabackend.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface ElementRepository extends JpaRepository<Element, Long>, CrudRepository<Element, Long> {
    List<Element> findAllByModuleId(Long id);
    List<Element> findAllByProfesseurElementsProfesseurId(Long id);
}
