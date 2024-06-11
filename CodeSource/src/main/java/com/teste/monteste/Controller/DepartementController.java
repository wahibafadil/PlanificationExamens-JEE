package com.teste.monteste.Controller;

import com.teste.monteste.Services.IDepartementService;
import com.teste.monteste.bo.Departement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/departements")
public class DepartementController {
    @Autowired
    private IDepartementService DepartementService;
    @GetMapping("/all")
    public String allDepartment(Model model)
    {
        List<Departement>departements=DepartementService.all();
        model.addAttribute("departements",departements);
        return "/Departement/all";

    }
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("departement", new Departement());
        return "/Departement/new";
    }
    @PostMapping("/save")
    public String saveDepartement(@ModelAttribute Departement departement) {
        departement.setDate_Ajout(new Date());
          DepartementService.ajouter(departement);
        return "redirect:/departements/all";
    }
    @GetMapping("/allfilieres")
    public String allfilieres(Model model)
    {
        model.addAttribute("departementsWithFilieres",DepartementService.getDepartementsWithFilieres());
        return"/Departement/departementwithfilieres";
    }





}
