package com.teste.monteste.Controller;

import com.teste.monteste.bo.Examen;
import com.teste.monteste.bo.Enseignant;
import com.teste.monteste.bo.ElementPedagogique;
import com.teste.monteste.bo.Salle;
import com.teste.monteste.Services.ExamenService;
import com.teste.monteste.Services.IEnseignantService;
import com.teste.monteste.Services.IModuleService;
import com.teste.monteste.Services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/examens")
public class ExamenController {
    @Autowired
    private ExamenService examenService;

    @Autowired
    private IEnseignantService enseignantService;

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private SalleService salleService;

    @GetMapping("/all")
    public String getAllExamens(Model model) {
    	System.out.println("alllllmmmmm");

        List<Examen> examens = examenService.getAllExamens();
        model.addAttribute("examens", examens);
        return "examens/all";
    }

    @GetMapping("/new")
    public String createExamenForm(Model model) {
    	System.out.println("newwwwwwwwww");
    	System.out.println(111111);

        Examen examen = new Examen();
        List<Enseignant> enseignants = enseignantService.all();
        List<ElementPedagogique> modules = moduleService.getAllModules();
        List<Salle> salles = salleService.getAllSalles();
        model.addAttribute("examen", examen);
        model.addAttribute("enseignants", enseignants);
        model.addAttribute("modules", modules);
        model.addAttribute("salles", salles);
        
        
       
        return "examens/new";
    }

    @PostMapping("/save")
    public String createExamen(@ModelAttribute Examen examen) {
    	System.out.println(examen);
        if (examen.getCoordonnateur() == null || examen.getModule() == null) {
            // Ajoute une gestion d'erreur ici si nécessaire
        	System.out.println("gggggggg");
        	return "redirect:/examens/new?error=true";
        }
        examenService.saveExamen(examen);
        return "redirect:/examens/all";
    }

    @GetMapping("/edit/{id}")
    public String editExamenForm(@PathVariable Long id, Model model) {
        Examen examen = examenService.getExamenById(id);
        List<Enseignant> enseignants = enseignantService.all();
        List<ElementPedagogique> modules = moduleService.getAllModules();
        List<Salle> salles = salleService.getAllSalles();
        model.addAttribute("examen", examen);
        model.addAttribute("enseignants", enseignants);
        model.addAttribute("modules", modules);
        model.addAttribute("salles", salles);
        return "examens/edit";
    }

    @PostMapping("/update/{id}")
    public String updateExamen(@PathVariable Long id, @ModelAttribute Examen examen) {
        examen.setId(id);
        examenService.saveExamen(examen);
        return "redirect:/examens";
    }

    @GetMapping("/delete/{id}")
    public String deleteExamen(@PathVariable Long id) {
        examenService.deleteExamen(id);
        return "redirect:/examens";
    }
}
