package com.teste.monteste.bo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
    @Entity
    public class ElementPedagogique {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private  String type;
        private String description;


        private String nom;

        @ManyToMany
        @JoinTable(
                name = "module_filiere",
                joinColumns = @JoinColumn(name = "module_id"),
                inverseJoinColumns = @JoinColumn(name = "filiere_id")

        )
        private List<Filiere> filieres=new ArrayList<>();
        @ManyToMany
        @JoinTable(
                name = "module_niveau",
                joinColumns = @JoinColumn(name = "module_id"),
                inverseJoinColumns = @JoinColumn(name = "niveau_id")
        )
        private List<Niveau> niveaux = new ArrayList<>();
        @OneToMany(mappedBy = "module")
        private Set<EnseignantModuleFiliereNiveau> affectations = new HashSet<>();

        @ManyToOne
        @JoinColumn(name = "enseignant_id")
        private Enseignant enseignant;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public List<Filiere> getFilieres() {
            return filieres;
        }

        public void setFilieres(List<Filiere> filieres) {
            this.filieres = filieres;
        }

        public Enseignant getEnseignant() {
            return enseignant;
        }

        public void setEnseignant(Enseignant enseignant) {
            this.enseignant = enseignant;
        }

        public List<Niveau> getNiveaux() {
            return niveaux;
        }

        public void setNiveaux(List<Niveau> niveaux) {
            this.niveaux = niveaux;
        }


        public Set<EnseignantModuleFiliereNiveau> getAffectations() {
            return affectations;
        }

        public void setAffectations(Set<EnseignantModuleFiliereNiveau> affectations) {
            this.affectations = affectations;
        }
    }
