package com.examen.appds.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
public class AnneeAcademique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    
    @OneToMany(mappedBy = "anneeAcademique", cascade = CascadeType.ALL)
    private List<Semestre> semestres = new ArrayList<>();

    public AnneeAcademique() {}

    public AnneeAcademique(Long id, String libelle, LocalDate dateDebut, LocalDate dateFin, List<Semestre> semestres) {
        this.id = id;
        this.libelle = libelle;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.semestres = semestres != null ? semestres : new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public List<Semestre> getSemestres() { return semestres; }
    public void setSemestres(List<Semestre> semestres) { this.semestres = semestres; }
}