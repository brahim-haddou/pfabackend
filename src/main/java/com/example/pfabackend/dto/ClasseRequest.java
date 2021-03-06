package com.example.pfabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseRequest {
    private Long id;
    private String nom;
    private String type;
    private int maxEtudiant;
    private Long elementId;
}
