package com.teste.monteste.Controller;


import com.teste.monteste.Services.IDepartementService;
import com.teste.monteste.Services.IEPService;
import com.teste.monteste.Services.IEnseignantService;
import com.teste.monteste.Services.IFiliereService;
import com.teste.monteste.bo.Departement;
import com.teste.monteste.bo.ElementPedagogique;
import com.teste.monteste.bo.Enseignant;
import com.teste.monteste.bo.Filiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/filiere")
public class FiliereController {
    @Autowired
    private IFiliereService FiliereService;
    @Autowired
    private IDepartementService DepartementService;
    @Autowired
    private IEnseignantService EnseignantService;
    @Autowired
    private IEPService ElementPService;



    @GetMapping("/all")
    public String listFilieres(Model model) {
        List<Filiere> filieres = FiliereService.all();
        model.addAttribute("filieres", filieres);
        return "/Filiere/all";
    }
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("filiere", new Filiere());
        List<Departement> departements = DepartementService.all();
        model.addAttribute("departements", departements);

        return "/Filiere/new";
    }

    @PostMapping("/save")
    public String saveFiliere(@ModelAttribute Filiere filiere) {
        filiere.setDate_ajout(new Date());
        FiliereService.ajouter(filiere);
        return "redirect:/filiere/all"; // Redirection vers une page affichant toutes les filières
    }
    @GetMapping("/{id}")
    public String getFiliereDetails(@PathVariable Long id, Model model) {
        Filiere filiere = FiliereService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid filiere Id:" + id));
        model.addAttribute("filiere", filiere);
        return "/Filiere/filiereDetails";

    }
    @GetMapping("/{id}/niveaux")
    public String getFiliereNiveaux(@PathVariable Long id, Model model) {
        System.out.println();
        Optional<Filiere> filiere = FiliereService.findById(id);
        model.addAttribute("filiere", filiere.get());
        model.addAttribute("niveaux", filiere.get().getNiveaux());
        return "/Filiere/filiereniveau";
    }
    @PostMapping("/selection")
    public String handleSelection(@RequestParam Long filiereId, @RequestParam Long niveauId, Model model) {

        System.out.println("Filiere ID: " + filiereId);
        System.out.println("Niveau ID: " + niveauId);
        Optional<Filiere> filier=FiliereService.findById(filiereId);


        List<ElementPedagogique> findModulesByFiliereAndNiveau=ElementPService.findModulesByFiliereAndNiveau( filiereId, niveauId) ;
        for(ElementPedagogique e:findModulesByFiliereAndNiveau)
        {
            System.out.println(e.getNom());
        }



        model.addAttribute("message", "Sélection réussie pour la filière ID: " + filiereId + " et le niveau ID: " + niveauId);
        return "confirmation"; // Assurez-vous d'avoir une vue "confirmation.html"
    }
}



