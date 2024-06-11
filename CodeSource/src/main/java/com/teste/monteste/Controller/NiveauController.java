package com.teste.monteste.Controller;

import com.teste.monteste.Services.IFiliereService;
import com.teste.monteste.Services.INiveauService;
import com.teste.monteste.bo.Niveau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/niveau")
public class NiveauController {
    @Autowired
    private IFiliereService FiliereService;
    @Autowired
    private INiveauService niveauService;


    @GetMapping("/new")
    public String showCreateNiveauForm(Model model) {
        model.addAttribute("niveau", new Niveau());
        model.addAttribute("filieres", FiliereService.all());
        return "/Niveau/new";
    }
    @PostMapping("/save")
    public String createNiveau(@ModelAttribute Niveau niveau) {
        niveauService.ajouter(niveau);
        return "/Niveau/new";
    }
}

