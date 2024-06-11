package com.teste.monteste.bo;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private Date date_ajout;
    private String Abreviation;

    @OneToMany(mappedBy = "filiere")
    private Set<EnseignantModuleFiliereNiveau> affectations = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    @ManyToMany(mappedBy = "filieres")
    private List<ElementPedagogique> elementPedagogiques=new ArrayList<>();
    @OneToMany(mappedBy = "filiere")
    private List<Niveau> niveaux = new ArrayList<>();
    @ManyToMany(mappedBy = "filieres")
    private Set<Enseignant> enseignants;




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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    public String getAbreviation() {
        return Abreviation;
    }

    public void setAbreviation(String abreviation) {
        Abreviation = abreviation;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public List<ElementPedagogique> getElementPedagogiques() {
        return elementPedagogiques;
    }

    public void setElementPedagogiques(List<ElementPedagogique> elementPedagogiques) {
        this.elementPedagogiques = elementPedagogiques;
    }


    public List<Niveau> getNiveaux() {
        return niveaux;
    }

    public void setNiveaux(List<Niveau> niveaux) {
        this.niveaux = niveaux;
    }

    public Set<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(Set<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public Set<EnseignantModuleFiliereNiveau> getAffectations() {
        return affectations;
    }

    public void setAffectations(Set<EnseignantModuleFiliereNiveau> affectations) {
        this.affectations = affectations;
    }
}