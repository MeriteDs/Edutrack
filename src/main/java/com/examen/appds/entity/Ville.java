package com.examen.appds.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    
    @OneToMany(mappedBy = "ville", cascade = CascadeType.ALL)
    private List<Universite> universites = new ArrayList<>();

    public Ville() {}

    public Ville(Long id, String nom, List<Universite> universites) {
        this.id = id;
        this.nom = nom;
        this.universites = universites != null ? universites : new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public List<Universite> getUniversites() { return universites; }
    public void setUniversites(List<Universite> universites) { this.universites = universites; }
}