package com.example.pfabackend.repository;

import com.example.pfabackend.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository  extends JpaRepository<Module, Long> {
}
