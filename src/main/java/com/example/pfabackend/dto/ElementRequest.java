package com.example.pfabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementRequest {
    private Long id;
    private String nom;
    private Long module_id;
}
