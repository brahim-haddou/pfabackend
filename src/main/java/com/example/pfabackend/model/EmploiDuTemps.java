package com.example.pfabackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EmploiDuTemps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "classe_id", referencedColumnName = "id")
    private Classe classe;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professeur_id", referencedColumnName = "id")
    private Professeur professeur;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salle_id", referencedColumnName = "id")
    private Salle salle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creneau_id", referencedColumnName = "id")
    private Creneau creneau;
}
