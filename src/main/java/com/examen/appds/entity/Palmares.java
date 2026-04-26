package com.examen.appds.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Palmares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate dateInscription;
    private LocalDate dateDiplome;  
    private Double moyenneGenerale;
    private String mention;        
    
    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;
    
    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;
    
    @OneToMany(mappedBy = "palmares", cascade = CascadeType.ALL)
    private List<Resultat> resultats = new ArrayList<>();

    public Palmares() {}

    // Constructeur avec tous les champs (optionnel)
    public Palmares(Long id, LocalDate dateInscription, LocalDate dateDiplome, Double moyenneGenerale, String mention, Etudiant etudiant, Promotion promotion, List<Resultat> resultats) {
        this.id = id;
        this.dateInscription = dateInscription;
        this.dateDiplome = dateDiplome;
        this.moyenneGenerale = moyenneGenerale;
        this.mention = mention;
        this.etudiant = etudiant;
        this.promotion = promotion;
        this.resultats = resultats != null ? resultats : new ArrayList<>();
    }

    // Builder manuel
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private Long id;
        private LocalDate dateInscription;
        private LocalDate dateDiplome;
        private Double moyenneGenerale;
        private String mention;
        private Etudiant etudiant;
        private Promotion promotion;
        private List<Resultat> resultats = new ArrayList<>();
        
        public Builder id(Long id) { this.id = id; return this; }
        public Builder dateInscription(LocalDate dateInscription) { this.dateInscription = dateInscription; return this; }
        public Builder dateDiplome(LocalDate dateDiplome) { this.dateDiplome = dateDiplome; return this; }
        public Builder moyenneGenerale(Double moyenneGenerale) { this.moyenneGenerale = moyenneGenerale; return this; }
        public Builder mention(String mention) { this.mention = mention; return this; }
        public Builder etudiant(Etudiant etudiant) { this.etudiant = etudiant; return this; }
        public Builder promotion(Promotion promotion) { this.promotion = promotion; return this; }
        public Builder resultats(List<Resultat> resultats) { this.resultats = resultats; return this; }
        
        public Palmares build() {
            return new Palmares(id, dateInscription, dateDiplome, moyenneGenerale, mention, etudiant, promotion, resultats);
        }
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDateInscription() { return dateInscription; }
    public void setDateInscription(LocalDate dateInscription) { this.dateInscription = dateInscription; }
    public LocalDate getDateDiplome() { return dateDiplome; }
    public void setDateDiplome(LocalDate dateDiplome) { this.dateDiplome = dateDiplome; }
    public Double getMoyenneGenerale() { return moyenneGenerale; }
    public void setMoyenneGenerale(Double moyenneGenerale) { this.moyenneGenerale = moyenneGenerale; }
    public String getMention() { return mention; }
    public void setMention(String mention) { this.mention = mention; }
    public Etudiant getEtudiant() { return etudiant; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }
    public Promotion getPromotion() { return promotion; }
    public void setPromotion(Promotion promotion) { this.promotion = promotion; }
    public List<Resultat> getResultats() { return resultats; }
    public void setResultats(List<Resultat> resultats) { this.resultats = resultats; }
}