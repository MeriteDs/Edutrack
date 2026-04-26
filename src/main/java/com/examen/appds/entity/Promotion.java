package com.examen.appds.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    
    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;
    
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    private List<Palmares> palmaresList = new ArrayList<>();

    public Promotion() {}

    public Promotion(Long id, String nom, Departement departement, List<Palmares> palmaresList) {
        this.id = id;
        this.nom = nom;
        this.departement = departement;
        this.palmaresList = palmaresList != null ? palmaresList : new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Departement getDepartement() { return departement; }
    public void setDepartement(Departement departement) { this.departement = departement; }
    public List<Palmares> getPalmaresList() { return palmaresList; }
    public void setPalmaresList(List<Palmares> palmaresList) { this.palmaresList = palmaresList; }
}