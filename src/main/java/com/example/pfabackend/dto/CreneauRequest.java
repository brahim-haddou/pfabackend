package com.example.pfabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreneauRequest {

    private String jour;
    private float debut;
    private float fin;
    private Long filiereId;
}
