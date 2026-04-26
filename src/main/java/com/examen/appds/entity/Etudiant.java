package com.examen.appds.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    private String prenom;
    
    @Column(unique = true, nullable = false)
    private String matricule;
    
    private LocalDate dateNaissance;
    
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Palmares> palmaresList = new ArrayList<>();

    public Etudiant() {}

    public Etudiant(Long id, String nom, String prenom, String matricule, LocalDate dateNaissance, List<Palmares> palmaresList) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.dateNaissance = dateNaissance;
        this.palmaresList = palmaresList != null ? palmaresList : new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public List<Palmares> getPalmaresList() { return palmaresList; }
    public void setPalmaresList(List<Palmares> palmaresList) { this.palmaresList = palmaresList; }
}