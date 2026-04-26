package com.examen.appds.controller;

import com.examen.appds.entity.Etudiant;
import com.examen.appds.service.EtudiantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/etudiant")
public class EtudiantController {
    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @GetMapping("/search")
    public String searchForm() {
        return "search";
    }

    @GetMapping("/parcours")
    public String showParcours(@RequestParam(value = "nom", required = false) String nom,
                               @RequestParam(value = "matricule", required = false) String matricule,
                               Model model) {
        Etudiant etudiant = null;
        if (matricule != null && !matricule.isEmpty()) {
            etudiant = etudiantService.findByMatricule(matricule).orElse(null);
        } else if (nom != null && !nom.isEmpty()) {
            List<Etudiant> etudiants = etudiantService.findByNom(nom);
            if (!etudiants.isEmpty()) {
                etudiant = etudiants.get(0);
            }
        }

        if (etudiant == null) {
            model.addAttribute("error", "Aucun étudiant trouvé avec ces informations.");
            return "search";
        }

        model.addAttribute("etudiant", etudiant);
        model.addAttribute("parcours", etudiant.getPalmaresList());
        return "parcours";
    }
}