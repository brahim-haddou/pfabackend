package com.example.pfabackend.dto;

import com.example.pfabackend.model.EmploiDuTemps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClasseRequest {
    private Long id;
    private String nom;
    private String type;
    private int maxEtudiant;
    private Long element_id;
    private Set<EmploiDuTemps> emploiDuTemps = new HashSet();
}
