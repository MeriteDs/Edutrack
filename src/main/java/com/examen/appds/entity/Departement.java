package com.examen.appds.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    
    @ManyToOne
    @JoinColumn(name = "faculte_id")
    private Faculte faculte;
    
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private List<Promotion> promotions = new ArrayList<>();

    public Departement() {}

    public Departement(Long id, String nom, Faculte faculte, List<Promotion> promotions) {
        this.id = id;
        this.nom = nom;
        this.faculte = faculte;
        this.promotions = promotions != null ? promotions : new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Faculte getFaculte() { return faculte; }
    public void setFaculte(Faculte faculte) { this.faculte = faculte; }
    public List<Promotion> getPromotions() { return promotions; }
    public void setPromotions(List<Promotion> promotions) { this.promotions = promotions; }
}