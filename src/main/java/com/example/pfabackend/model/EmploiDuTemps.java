package com.example.pfabackend.model;

import lombok.AllArgsConstructor;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "classe_id", referencedColumnName = "id")
    private Classe classe;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "professeur_id", referencedColumnName = "id")
    private Professeur professeur;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "salle_id", referencedColumnName = "id")
    private Salle salle;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "creneau_id", referencedColumnName = "id")
    private Creneau creneau;

}
