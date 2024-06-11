package com.teste.monteste.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.teste.monteste.bo.PersonnelAdministratif;
import com.teste.monteste.bo.Enseignant;
import com.teste.monteste.bo.Personnel;
import com.teste.monteste.Services.IPersonnelService;
import com.teste.monteste.utils.ExclExporter;
import com.teste.monteste.web.models.PersonnelModel;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class PersonnelMngController {

    @Autowired
    private IPersonnelService personService;

    @Autowired
    private HttpSession httpSession;

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    public PersonnelMngController() {}

    @GetMapping(value = "showForm")
    public String showForm(@RequestParam int typePerson, Model model) {
        PersonnelModel pmodel = new PersonnelModel(typePerson);
        model.addAttribute("personModel", pmodel);

        List<Personnel> persons = personService.getAllPersonnes();
        List<PersonnelModel> modelPersons = new ArrayList<>();
        for (Personnel person : persons) {
            PersonnelModel pm = new PersonnelModel();
            if (person instanceof Enseignant) {
                BeanUtils.copyProperties((Enseignant) person, pm);
                pm.setTypePerson(PersonnelModel.TYPE_ENSEIGNANT);
                modelPersons.add(pm);
            } else if (person instanceof PersonnelAdministratif) {
                BeanUtils.copyProperties((PersonnelAdministratif) person, pm);
                pm.setTypePerson(PersonnelModel.TYPE_ADMIN);
                modelPersons.add(pm);
            }
        }
        model.addAttribute("personList", modelPersons);
        return "admin/form";
    }

    @RequestMapping(value = "addPerson", method = RequestMethod.POST)
    public String process(@Valid @ModelAttribute("personModel") PersonnelModel person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/form";
        }
        if (person.getTypePerson() == PersonnelModel.TYPE_ENSEIGNANT) {
            Enseignant prof = new Enseignant();
            BeanUtils.copyProperties(person, prof);
            personService.addPersonne(prof);
        } else if (person.getTypePerson() == PersonnelModel.TYPE_ADMIN) {
        	PersonnelAdministratif ca = new PersonnelAdministratif();
            BeanUtils.copyProperties(person, ca);
            personService.addPersonne(ca);
        }
        return "redirect:/admin/showForm?typePerson=" + person.getTypePerson();
    }

    @RequestMapping(value = "updatePersonForm/{idPerson}", method = RequestMethod.GET)
    public String updatePersonForm(@PathVariable("idPerson") int idPerson, Model model) {
        Personnel utl = personService.getPersonneById(Long.valueOf(idPerson));
        PersonnelModel pm = new PersonnelModel();
        if (utl instanceof Enseignant) {
            BeanUtils.copyProperties((Enseignant) utl, pm);
            pm.setTypePerson(PersonnelModel.TYPE_ENSEIGNANT);
        } else if (utl instanceof PersonnelAdministratif) {
            BeanUtils.copyProperties((PersonnelAdministratif) utl, pm);
            pm.setTypePerson(PersonnelModel.TYPE_ADMIN);
        }
        model.addAttribute("personModel", pm);
        return "admin/updateForm";
    }

    @RequestMapping(value = "searchPerson", method = RequestMethod.GET)
    public String searchPerson(@RequestParam String cin, Model model) {
        Personnel utl = personService.getPersonneByCin(cin);
        if (utl == null) {
            model.addAttribute("personModel", new ArrayList<PersonnelModel>());
        } else {
            PersonnelModel pm = new PersonnelModel();
            if (utl instanceof Enseignant) {
                BeanUtils.copyProperties((Enseignant) utl, pm);
                pm.setTypePerson(PersonnelModel.TYPE_ENSEIGNANT);
            } else if (utl instanceof PersonnelAdministratif) {
                BeanUtils.copyProperties((PersonnelAdministratif) utl, pm);
                pm.setTypePerson(PersonnelModel.TYPE_ADMIN);
            }
            List<PersonnelModel> modelPersons = new ArrayList<>();
            modelPersons.add(pm);
            model.addAttribute("personList", modelPersons);
        }
        return "admin/listPersons";
    }

    @RequestMapping("updatePerson")
    public String updatePerson(@Valid @ModelAttribute("personModel") PersonnelModel person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/updateForm";
        }
        if (person.getTypePerson() == PersonnelModel.TYPE_ENSEIGNANT) {
            Enseignant prof = new Enseignant();
            BeanUtils.copyProperties(person, prof);
            personService.updatePersonne(prof);
        } else if (person.getTypePerson() == PersonnelModel.TYPE_ADMIN) {
        	PersonnelAdministratif ca = new PersonnelAdministratif();
            BeanUtils.copyProperties(person, ca);
            personService.updatePersonne(ca);
        }
        model.addAttribute("msg", "Opération effectuée avec succès");
        return "admin/updateForm";
    }

    @RequestMapping("managePersons")
    public String managePersons(Model model) {
        List<Personnel> persons = personService.getAllPersonnes();
        List<PersonnelModel> modelPersons = new ArrayList<>();
        for (Personnel person : persons) {
            PersonnelModel pm = new PersonnelModel();
            if (person instanceof Enseignant) {
                BeanUtils.copyProperties((Enseignant) person, pm);
                pm.setTypePerson(PersonnelModel.TYPE_ENSEIGNANT);
                modelPersons.add(pm);
            } else if (person instanceof PersonnelAdministratif) {
                BeanUtils.copyProperties((PersonnelAdministratif) person, pm);
                pm.setTypePerson(PersonnelModel.TYPE_ADMIN);
                modelPersons.add(pm);
            }
        }
        model.addAttribute("personList", modelPersons);
        return "admin/listPersons";
    }

    @RequestMapping(value = "deletePerson/{idPerson}", method = RequestMethod.GET)
    public String delete(@PathVariable int idPerson) {
        personService.deletePersonne(Long.valueOf(idPerson));
        return "redirect:/admin/managePersons";
    }

    @GetMapping("exportPersons")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Personnel> persons = personService.getAllPersonnes();
        ExclExporter excelExporter = personService.preparePersonneExport(persons);

        excelExporter.export(response);
    }
}
