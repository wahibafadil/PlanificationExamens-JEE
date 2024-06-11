package com.teste.monteste.Controller;

import com.teste.monteste.bo.Groupe;
import com.teste.monteste.bo.Enseignant;
import com.teste.monteste.Services.GroupeService;
import com.teste.monteste.Services.IEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Groupe")
public class GroupeController {

	 @Autowired
	    private GroupeService groupeService;

	    @Autowired
	    private IEnseignantService enseignantService;

	    @GetMapping("all")
	    public String getAllGroupes(Model model) {
	        model.addAttribute("groupes", groupeService.getAllGroupes());
	        return "Groupe/all";
	    }

	    @GetMapping("new")
	    public String createGroupeForm(Model model) {
	        model.addAttribute("groupe", new Groupe());
	        model.addAttribute("enseignants", enseignantService.getAllEnseignants());
	        return "Groupe/new";
	    }

	    @PostMapping("save")
	    public String saveGroupe(@ModelAttribute("groupe") Groupe groupe) {
	        groupeService.saveGroupe(groupe);
	        return "redirect:/Groupe/all";
	    }
    @GetMapping("/{id}/addEnseignant")
    public String showAddEnseignantForm(@PathVariable Long id, Model model) {
        Groupe groupe = groupeService.getGroupeById(id);
        model.addAttribute("groupe", groupe);
        model.addAttribute("enseignants", enseignantService.getAllEnseignants());
        return "Groupe/addEnseignant";
    }

    @PostMapping("/{id}/addEnseignant")
    public String addEnseignantToGroupe(@PathVariable Long id, @RequestParam Long enseignantId) {
        Groupe groupe = groupeService.getGroupeById(id);
        Enseignant enseignant = enseignantService.getEnseignantById(enseignantId);
        groupe.getEnseignants().add(enseignant);
        enseignant.setGroupe(groupe);
        groupeService.saveGroupe(groupe);
        return "redirect:/Groupe/all";
    }
}
