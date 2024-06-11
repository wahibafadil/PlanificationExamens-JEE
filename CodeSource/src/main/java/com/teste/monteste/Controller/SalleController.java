package com.teste.monteste.Controller;

import com.teste.monteste.bo.Salle;
import com.teste.monteste.Services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salles")
public class SalleController {
    @Autowired
    private SalleService salleService;

    @PostMapping
    public Salle createSalle(@RequestBody Salle salle) {
        return salleService.saveSalle(salle);
    }

    @GetMapping
    public List<Salle> getAllSalles() {
        return salleService.getAllSalles();
    }

    @GetMapping("/{id}")
    public Salle getSalleById(@PathVariable Long id) {
        return salleService.getSalleById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSalle(@PathVariable Long id) {
        salleService.deleteSalle(id);
    }

    // Other endpoints for business logic
}
