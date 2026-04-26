package com.examen.appds.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
public class Faculte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    
    @ManyToOne
    @JoinColumn(name = "universite_id")
    private Universite universite;
    
    @OneToMany(mappedBy = "faculte", cascade = CascadeType.ALL)
    private List<Departement> departements = new ArrayList<>();

    public Faculte() {}

    public Faculte(Long id, String nom, Universite universite, List<Departement> departements) {
        this.id = id;
        this.nom = nom;
        this.universite = universite;
        this.departements = departements != null ? departements : new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Universite getUniversite() { return universite; }
    public void setUniversite(Universite universite) { this.universite = universite; }
    public List<Departement> getDepartements() { return departements; }
    public void setDepartements(List<Departement> departements) { this.departements = departements; }
}