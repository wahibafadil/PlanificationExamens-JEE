package com.teste.monteste.bo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Niveau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;
    @OneToMany(mappedBy = "niveau")
    private Set<EnseignantModuleFiliereNiveau> affectations = new HashSet<>();

    @ManyToMany(mappedBy = "niveaux")
    private List<ElementPedagogique> modules = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public List<ElementPedagogique> getModules() {
        return modules;
    }

    public void setModules(List<ElementPedagogique> modules) {
        this.modules = modules;
    }


    public Set<EnseignantModuleFiliereNiveau> getAffectations() {
        return affectations;
    }

    public void setAffectations(Set<EnseignantModuleFiliereNiveau> affectations) {
        this.affectations = affectations;
    }
}
