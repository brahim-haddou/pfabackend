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
public class Creneau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jour;
    private float debut;
    private float fin;

    @JsonIgnore
    @OneToMany(mappedBy ="creneau", cascade=CascadeType.ALL)
    private Set<EmploiDuTemps> emploiDuTemps = new HashSet();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "filiere_id", referencedColumnName = "id")
    private Filiere filiere;
}
