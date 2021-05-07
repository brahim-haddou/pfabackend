package com.example.pfabackend.repository;

import com.example.pfabackend.model.Element;
import com.example.pfabackend.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ElementRepository extends JpaRepository<Element, Long> {

    List<Element> findAllByElementId(Long id);
}
