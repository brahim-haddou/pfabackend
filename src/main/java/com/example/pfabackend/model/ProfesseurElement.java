package com.example.pfabackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfesseurElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Professeur professeur;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Element element;

}
