package com.teste.monteste.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teste.monteste.Services.IPersonnelService;
import com.teste.monteste.utils.ExclExporter;
import com.teste.monteste.bo.Compte;
import com.teste.monteste.Services.ICompteService;
import com.teste.monteste.web.models.CompteModel;

@Controller
@RequestMapping("/admin")
public class CompteController {

    @Autowired
    private ICompteService userService;

    @Autowired
    private IPersonnelService personService;

    @RequestMapping(value = "createAccountForm/{idPerson}", method = RequestMethod.GET)
    public String createAccountForm(@PathVariable int idPerson, Model model) {
        CompteModel accountModel = new CompteModel(Long.valueOf(idPerson));
        model.addAttribute("accountModel", accountModel);
        model.addAttribute("roleList", userService.getAllRoles());
        model.addAttribute("accountList", userService.getAllAccounts());
        return "admin/formAccount"; // Assurez-vous que ce fichier existe dans src/main/resources/templates/admin
    }

    @GetMapping("manageAccounts")
    public String manageAccounts(@ModelAttribute("accountModel") CompteModel accountModel, Model model) {
        model.addAttribute("accountList", userService.getAllAccounts());
        return "admin/accountList"; // Assurez-vous que ce fichier existe dans src/main/resources/templates/admin
    }

    @GetMapping("createAccounts")
    public String createAccounts(@ModelAttribute("accountModel") CompteModel accountModel, Model model) {
        model.addAttribute("personList", personService.getAllPersonnes());
        return "admin/accountCreation"; // Assurez-vous que ce fichier existe dans src/main/resources/templates/admin
    }

    @PostMapping("addAccount")
    public String addAccount(@ModelAttribute("accountModel") CompteModel accountModel, Model model) {
        String password = userService.createUser(accountModel.getRoleId(), accountModel.getPersonId());
        accountModel.setPassword(password);
        model.addAttribute("accountList", userService.getAllAccounts());
        return "admin/accountList"; // Assurez-vous que ce fichier existe dans src/main/resources/templates/admin
    }

    @GetMapping("exportAccounts")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=accounts_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Compte> comptes = userService.getAllAccounts();
        ExclExporter excelExporter = userService.prepareCompteExport(comptes);

        excelExporter.export(response);
    }
}

