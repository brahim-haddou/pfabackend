package com.example.pfabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmploiDuTempsRequest {
    Long id;
    Long classeId;
    Long professeurId;
    Long salleId;
    Long creneauId;
}
