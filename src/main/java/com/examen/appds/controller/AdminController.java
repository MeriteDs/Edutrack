package com.examen.appds.controller;

import com.examen.appds.entity.*;
import com.examen.appds.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final EtudiantRepository etudiantRepository;
    private final UniversiteRepository universiteRepository;
    private final FaculteRepository faculteRepository;
    private final DepartementRepository departementRepository;
    private final PromotionRepository promotionRepository;
    private final AnneeAcademiqueRepository anneeAcademiqueRepository;
    private final SemestreRepository semestreRepository;
    private final PalmaresRepository palmaresRepository;
    private final ResultatRepository resultatRepository;
    private final VilleRepository villeRepository;

    public AdminController(EtudiantRepository etudiantRepository,
                           UniversiteRepository universiteRepository,
                           FaculteRepository faculteRepository,
                           DepartementRepository departementRepository,
                           PromotionRepository promotionRepository,
                           AnneeAcademiqueRepository anneeAcademiqueRepository,
                           SemestreRepository semestreRepository,
                           PalmaresRepository palmaresRepository,
                           ResultatRepository resultatRepository,
                           VilleRepository villeRepository) {
        this.etudiantRepository = etudiantRepository;
        this.universiteRepository = universiteRepository;
        this.faculteRepository = faculteRepository;
        this.departementRepository = departementRepository;
        this.promotionRepository = promotionRepository;
        this.anneeAcademiqueRepository = anneeAcademiqueRepository;
        this.semestreRepository = semestreRepository;
        this.palmaresRepository = palmaresRepository;
        this.resultatRepository = resultatRepository;
        this.villeRepository = villeRepository;
    }

    // =====================================================
    // AUTHENTIFICATION
    // =====================================================
    
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    // =====================================================
    // DASHBOARD
    // =====================================================
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalEtudiants", etudiantRepository.count());
        model.addAttribute("totalUniversites", universiteRepository.count());
        model.addAttribute("totalFacultes", faculteRepository.count());
        model.addAttribute("totalPromotions", promotionRepository.count());
        model.addAttribute("recentEtudiants", etudiantRepository.findAll().stream().limit(5).toList());
        return "admin/dashboard";
    }

    // =====================================================
    // GESTION DES ETUDIANTS
    // =====================================================
    
    @GetMapping("/etudiants")
    public String listEtudiants(Model model) {
        model.addAttribute("etudiants", etudiantRepository.findAll());
        return "admin/etudiants/list";
    }

    @GetMapping("/etudiants/add")
    public String addEtudiantForm(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "admin/etudiants/add";
    }

    @PostMapping("/etudiants/add")
    public String addEtudiant(@ModelAttribute Etudiant etudiant) {
        etudiantRepository.save(etudiant);
        return "redirect:/admin/etudiants";
    }

    @GetMapping("/etudiants/edit/{id}")
    public String editEtudiantForm(@PathVariable Long id, Model model) {
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        model.addAttribute("etudiant", etudiant);
        model.addAttribute("parcoursList", palmaresRepository.findByEtudiantId(id));
        return "admin/etudiants/edit";
    }

    @PostMapping("/etudiants/edit/{id}")
    public String editEtudiant(@PathVariable Long id, @ModelAttribute Etudiant etudiant) {
        etudiant.setId(id);
        etudiantRepository.save(etudiant);
        return "redirect:/admin/etudiants";
    }

    @GetMapping("/etudiants/delete/{id}")
    public String deleteEtudiant(@PathVariable Long id) {
        etudiantRepository.deleteById(id);
        return "redirect:/admin/etudiants";
    }

    // =====================================================
    // GESTION DES PARCOURS
    // =====================================================
    
    @GetMapping("/parcours/add/{etudiantId}")
    public String addParcoursForm(@PathVariable Long etudiantId, Model model) {
        model.addAttribute("etudiantId", etudiantId);
        model.addAttribute("promotions", promotionRepository.findAll());
        model.addAttribute("palmares", new Palmares());
        return "admin/parcours/add";
    }

    @PostMapping("/parcours/add/{etudiantId}")
    public String addParcours(@PathVariable Long etudiantId,
                              @RequestParam Long promotionId,
                              @RequestParam String dateInscription,
                              @RequestParam(required = false) String dateDiplome,
                              @RequestParam(required = false) Double moyenneGenerale,
                              @RequestParam(required = false) String mention) {
        
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
        Promotion promotion = promotionRepository.findById(promotionId).orElse(null);
        
        if (etudiant != null && promotion != null) {
            Palmares palmares = new Palmares();
            palmares.setDateInscription(LocalDate.parse(dateInscription));
            if (dateDiplome != null && !dateDiplome.isEmpty()) {
                palmares.setDateDiplome(LocalDate.parse(dateDiplome));
            }
            palmares.setMoyenneGenerale(moyenneGenerale);
            palmares.setMention(mention);
            palmares.setEtudiant(etudiant);
            palmares.setPromotion(promotion);
            palmaresRepository.save(palmares);
        }
        return "redirect:/admin/etudiants/edit/" + etudiantId;
    }

    @GetMapping("/parcours/delete/{id}")
    public String deleteParcours(@PathVariable Long id) {
        Palmares palmares = palmaresRepository.findById(id).orElse(null);
        Long etudiantId = palmares != null ? palmares.getEtudiant().getId() : null;
        palmaresRepository.deleteById(id);
        return "redirect:/admin/etudiants/edit/" + etudiantId;
    }

    // =====================================================
    // GESTION DES RESULTATS
    // =====================================================
    
    @GetMapping("/resultats/add/{palmaresId}")
    public String addResultatForm(@PathVariable Long palmaresId, Model model) {
        model.addAttribute("palmaresId", palmaresId);
        model.addAttribute("semestres", semestreRepository.findAll());
        model.addAttribute("resultat", new Resultat());
        return "admin/resultats/add";
    }

    @PostMapping("/resultats/add/{palmaresId}")
    public String addResultat(@PathVariable Long palmaresId,
                              @RequestParam Long semestreId,
                              @RequestParam Double note,
                              @RequestParam String mention) {
        
        Palmares palmares = palmaresRepository.findById(palmaresId).orElse(null);
        Semestre semestre = semestreRepository.findById(semestreId).orElse(null);
        
        if (palmares != null && semestre != null) {
            Resultat resultat = new Resultat();
            resultat.setNote(note);
            resultat.setMention(mention);
            resultat.setPalmares(palmares);
            resultat.setSemestre(semestre);
            resultatRepository.save(resultat);
        }
        
        Long etudiantId = palmares != null ? palmares.getEtudiant().getId() : null;
        return "redirect:/admin/etudiants/edit/" + etudiantId;
    }

    @GetMapping("/resultats/delete/{id}")
    public String deleteResultat(@PathVariable Long id) {
        Resultat resultat = resultatRepository.findById(id).orElse(null);
        Long etudiantId = null;
        if (resultat != null && resultat.getPalmares() != null) {
            etudiantId = resultat.getPalmares().getEtudiant().getId();
        }
        resultatRepository.deleteById(id);
        return "redirect:/admin/etudiants/edit/" + etudiantId;
    }

    // =====================================================
    // GESTION DES UNIVERSITES
    // =====================================================
    
    @GetMapping("/universites")
    public String listUniversites(Model model) {
        model.addAttribute("universites", universiteRepository.findAll());
        model.addAttribute("villes", villeRepository.findAll());
        return "admin/universites/list";
    }

    @PostMapping("/universites/add")
    public String addUniversite(@RequestParam String nom, @RequestParam Long villeId) {
        Ville ville = villeRepository.findById(villeId).orElse(null);
        Universite universite = new Universite();
        universite.setNom(nom);
        universite.setVille(ville);
        universiteRepository.save(universite);
        return "redirect:/admin/universites";
    }

    @GetMapping("/universites/delete/{id}")
    public String deleteUniversite(@PathVariable Long id) {
        universiteRepository.deleteById(id);
        return "redirect:/admin/universites";
    }

    // =====================================================
    // GESTION DES FACULTES
    // =====================================================
    
    @GetMapping("/facultes")
    public String listFacultes(Model model) {
        model.addAttribute("facultes", faculteRepository.findAll());
        model.addAttribute("universites", universiteRepository.findAll());
        return "admin/facultes/list";
    }

    @PostMapping("/facultes/add")
    public String addFaculte(@RequestParam String nom, @RequestParam Long universiteId) {
        Universite universite = universiteRepository.findById(universiteId).orElse(null);
        Faculte faculte = new Faculte();
        faculte.setNom(nom);
        faculte.setUniversite(universite);
        faculteRepository.save(faculte);
        return "redirect:/admin/facultes";
    }

    @GetMapping("/facultes/delete/{id}")
    public String deleteFaculte(@PathVariable Long id) {
        faculteRepository.deleteById(id);
        return "redirect:/admin/facultes";
    }

    // =====================================================
    // GESTION DES DEPARTEMENTS
    // =====================================================
    
    @GetMapping("/departements")
    public String listDepartements(Model model) {
        model.addAttribute("departements", departementRepository.findAll());
        model.addAttribute("facultes", faculteRepository.findAll());
        return "admin/departements/list";
    }

    @PostMapping("/departements/add")
    public String addDepartement(@RequestParam String nom, @RequestParam Long faculteId) {
        Faculte faculte = faculteRepository.findById(faculteId).orElse(null);
        Departement departement = new Departement();
        departement.setNom(nom);
        departement.setFaculte(faculte);
        departementRepository.save(departement);
        return "redirect:/admin/departements";
    }

    @GetMapping("/departements/delete/{id}")
    public String deleteDepartement(@PathVariable Long id) {
        departementRepository.deleteById(id);
        return "redirect:/admin/departements";
    }

    // =====================================================
    // GESTION DES PROMOTIONS
    // =====================================================
    
    @GetMapping("/promotions")
    public String listPromotions(Model model) {
        model.addAttribute("promotions", promotionRepository.findAll());
        model.addAttribute("departements", departementRepository.findAll());
        return "admin/promotions/list";
    }

    @PostMapping("/promotions/add")
    public String addPromotion(@RequestParam String nom, @RequestParam Long departementId) {
        Departement departement = departementRepository.findById(departementId).orElse(null);
        Promotion promotion = new Promotion();
        promotion.setNom(nom);
        promotion.setDepartement(departement);
        promotionRepository.save(promotion);
        return "redirect:/admin/promotions";
    }

    @GetMapping("/promotions/delete/{id}")
    public String deletePromotion(@PathVariable Long id) {
        promotionRepository.deleteById(id);
        return "redirect:/admin/promotions";
    }

    // =====================================================
    // GESTION DES VILLES
    // =====================================================
    
    @GetMapping("/villes")
    public String listVilles(Model model) {
        model.addAttribute("villes", villeRepository.findAll());
        return "admin/villes/list";
    }

    @PostMapping("/villes/add")
    public String addVille(@RequestParam String nom) {
        Ville ville = new Ville();
        ville.setNom(nom);
        villeRepository.save(ville);
        return "redirect:/admin/villes";
    }

    @GetMapping("/villes/delete/{id}")
    public String deleteVille(@PathVariable Long id) {
        villeRepository.deleteById(id);
        return "redirect:/admin/villes";
    }
}