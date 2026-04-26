package com.examen.appds.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
public class Resultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double note;
    private String mention;
    
    @ManyToOne
    @JoinColumn(name = "palmares_id")
    private Palmares palmares;
    
    @ManyToOne
    @JoinColumn(name = "semestre_id")
    private Semestre semestre;

    public Resultat() {}

    public Resultat(Long id, Double note, String mention, Palmares palmares, Semestre semestre) {
        this.id = id;
        this.note = note;
        this.mention = mention;
        this.palmares = palmares;
        this.semestre = semestre;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getNote() { return note; }
    public void setNote(Double note) { this.note = note; }
    public String getMention() { return mention; }
    public void setMention(String mention) { this.mention = mention; }
    public Palmares getPalmares() { return palmares; }
    public void setPalmares(Palmares palmares) { this.palmares = palmares; }
    public Semestre getSemestre() { return semestre; }
    public void setSemestre(Semestre semestre) { this.semestre = semestre; }
}