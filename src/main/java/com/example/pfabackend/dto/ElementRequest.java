package com.example.pfabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementRequest {
    private Long id;
    private String nom;
    private Long moduleId;
    private List<Long> professeurs = new ArrayList<>();
}
