package com.examen.appds.service;

import com.examen.appds.entity.Etudiant;
import com.examen.appds.entity.Palmares;
import com.examen.appds.repository.EtudiantRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {
    private final EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public Optional<Etudiant> findByMatricule(String matricule) {
        return etudiantRepository.findByMatricule(matricule);
    }

    public List<Etudiant> findByNom(String nom) {
        return etudiantRepository.findByNomContainingIgnoreCase(nom);
    }

    public List<Palmares> getParcoursComplet(Etudiant etudiant) {
        return etudiant.getPalmaresList();
    }
}