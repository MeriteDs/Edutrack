package com.examen.appds.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
public class Universite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    
    @ManyToOne
    @JoinColumn(name = "ville_id")
    private Ville ville;
    
    @OneToMany(mappedBy = "universite", cascade = CascadeType.ALL)
    private List<Faculte> facultes = new ArrayList<>();

    public Universite() {}

    public Universite(Long id, String nom, Ville ville, List<Faculte> facultes) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
        this.facultes = facultes != null ? facultes : new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Ville getVille() { return ville; }
    public void setVille(Ville ville) { this.ville = ville; }
    public List<Faculte> getFacultes() { return facultes; }
    public void setFacultes(List<Faculte> facultes) { this.facultes = facultes; }
}