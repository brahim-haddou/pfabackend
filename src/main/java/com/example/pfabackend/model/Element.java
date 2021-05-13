package com.example.pfabackend.model;

import com.fasterxml.jackson.annotation.*;
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
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @JsonIgnore
    @OneToMany(mappedBy = "element")
    private Set<Classe> classes = new HashSet();


    @JsonIgnore
    @OneToMany(
            mappedBy = "element"
    )
    private Set<ProfesseurElement> professeurElements = new HashSet();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    private Module module;
    
}
