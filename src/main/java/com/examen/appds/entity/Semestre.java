package com.examen.appds.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
public class Semestre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    
    @ManyToOne
    @JoinColumn(name = "annee_academique_id")
    private AnneeAcademique anneeAcademique;
    
    @OneToMany(mappedBy = "semestre", cascade = CascadeType.ALL)
    private List<Resultat> resultats = new ArrayList<>();

    public Semestre() {}

    public Semestre(Long id, Integer numero, AnneeAcademique anneeAcademique, List<Resultat> resultats) {
        this.id = id;
        this.numero = numero;
        this.anneeAcademique = anneeAcademique;
        this.resultats = resultats != null ? resultats : new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public AnneeAcademique getAnneeAcademique() { return anneeAcademique; }
    public void setAnneeAcademique(AnneeAcademique anneeAcademique) { this.anneeAcademique = anneeAcademique; }
    public List<Resultat> getResultats() { return resultats; }
    public void setResultats(List<Resultat> resultats) { this.resultats = resultats; }
}