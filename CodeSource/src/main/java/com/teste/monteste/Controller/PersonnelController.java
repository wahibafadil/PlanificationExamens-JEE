package com.teste.monteste.Controller;

import com.teste.monteste.Services.IDepartementService;
import com.teste.monteste.Services.IEnseignantService;
import com.teste.monteste.Services.IFiliereService;
import com.teste.monteste.Services.IPersonnelAdministratifService;
import com.teste.monteste.bo.Enseignant;
import com.teste.monteste.bo.PersonnelAdministratif;
import com.teste.monteste.bo.PersonnelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("Personnel")
public class PersonnelController {
    @Autowired
    private IEnseignantService EnseignantService;
    @Autowired
    private IPersonnelAdministratifService PersonnelAdministratifService;
    @Autowired
    private IDepartementService DepartementService;
    @Autowired
    private IFiliereService FiliereService;


    private static final String UPLOADED_FOLDER = "path/to/uploaded/images/";
     @GetMapping("/allE")
    public String TousEnseignants(Model model)
     {
         List<Enseignant> enseignants = EnseignantService.all();
         model.addAttribute("enseignants", enseignants);
         return "/Personnel/allE";
     }
    @GetMapping("/allP")
    public String TousPersonelAdministratif(Model model)
    {
        List<PersonnelAdministratif> personnelsAdministratifs = PersonnelAdministratifService.all();
        model.addAttribute("personnelsAdministratifs", personnelsAdministratifs);
        return "/Personnel/allP";
    }
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("personnelDTO", new PersonnelDTO());
        model.addAttribute("departements",DepartementService.all());
        model.addAttribute("filieres",FiliereService.all());
        return "/Personnel/new";
    }


    @PostMapping("/save")
    public String savePersonnel(@ModelAttribute PersonnelDTO personnelDTO, @RequestParam("imageFile") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            try {
                // Enregistre le fichier sur le serveur
                byte[] bytes = imageFile.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + imageFile.getOriginalFilename());
                Files.write(path, bytes);
                personnelDTO.setPhoto(path.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.print("111111111"+personnelDTO.getSpecialite()+"11111111111111111111111111111111la commanc escahswdbjbwbdjwbdjbjd");

        if ("Enseignant".equals(personnelDTO.getType())) {
            System.out.println(personnelDTO.getFiliers());

            Enseignant enseignant = new Enseignant();
            enseignant.setNom(personnelDTO.getNom());
            enseignant.setType(personnelDTO.getType());
            enseignant.setPrenom(personnelDTO.getPrenom());
            enseignant.setSpecialite(personnelDTO.getSpecialite());
            enseignant.setCin(personnelDTO.getCin());
            enseignant.setDepartement(personnelDTO.getDepartement());
            enseignant.setEmail(personnelDTO.getEmail());
            enseignant.setPhoto(personnelDTO.getPhoto());
            enseignant.setFilieres(personnelDTO.getFiliers());


            System.out.print("la commanc escahswdbjbwbdjwbdjbjd"+ personnelDTO.getDepartement());
            EnseignantService.ajouter(enseignant);

            return "redirect:/Personnel/allE";

        }
        else
        {
            PersonnelAdministratif P=new PersonnelAdministratif();
            P.setNom(personnelDTO.getNom());
            P.setType(personnelDTO.getType());
            P.setPrenom(personnelDTO.getPrenom());
            P.setCin(personnelDTO.getCin());
            P.setEmail(personnelDTO.getEmail());
            P.setPhoto(personnelDTO.getPhoto());
            PersonnelAdministratifService.ajouter(P);
            return "redirect:/Personnel/allP";


        }
    }
}