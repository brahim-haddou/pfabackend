package com.example.pfabackend.model;

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
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String type;
    private int maxEtudiant;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "element_id", referencedColumnName = "id")
    private Element element;


    @OneToMany(mappedBy ="classe")
    private Set<EmploiDuTemps> emploiDuTemps = new HashSet();
}
