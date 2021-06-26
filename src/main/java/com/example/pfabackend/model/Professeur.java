package com.example.pfabackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Professeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;


    @JsonIgnore
    @OneToMany(
            mappedBy = "professeur",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    private Set<ProfesseurElement> professeurElements = new HashSet();

    @JsonIgnore
    @OneToMany(mappedBy ="professeur")
    private Set<EmploiDuTemps> emploiDuTemps = new HashSet();
}
