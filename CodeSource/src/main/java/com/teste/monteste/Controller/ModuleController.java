package com.teste.monteste.Controller;

import com.teste.monteste.Services.IEPService;
import com.teste.monteste.Services.IFiliereService;
import com.teste.monteste.Services.INiveauService;
import com.teste.monteste.bo.ElementPedagogique;

import com.teste.monteste.bo.Filiere;
import com.teste.monteste.bo.Niveau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/EP")
public class ModuleController {
    @Autowired
    private IEPService EPServcie;
//    @Autowired
//    private IDepartementService DepartementService;
    @Autowired
    private IFiliereService FiliereService;
    @Autowired
    private INiveauService NiveauService;

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("module", new ElementPedagogique());
        Map<Filiere, List<Niveau>> filieresWithNiveaux = EPServcie.getFilieresWithNiveaux();
        model.addAttribute("filieresWithNiveaux", filieresWithNiveaux);


        return "/Module/new";
    }
    @PostMapping("/save")
    public String saveModule(@ModelAttribute("module") ElementPedagogique elementPedagogique,
                             @RequestParam(value = "filiereIds", required = false) List<Long> filiereIds,
                             @RequestParam(value = "niveauIds", required = false) List<Long> niveauIds) {
        // Assurez-vous que les listes de filières et de niveaux ne sont pas nulles
        if (filiereIds != null && niveauIds != null) {
            // Récupérez les filières et niveaux correspondants aux IDs sélectionnés
            List<Filiere> filieres = FiliereService.FindAllById(filiereIds);
            List<Niveau> niveaux = NiveauService.FindAllById(niveauIds);

            // Associez les filières et niveaux au module
            elementPedagogique.setFilieres(filieres);
            elementPedagogique.setNiveaux(niveaux);
        }

        // Enregistrez le module
        EPServcie.ajouter(elementPedagogique);

        // Redirection vers une page affichant toutes les filières
        return "Module/all";
    }

}
