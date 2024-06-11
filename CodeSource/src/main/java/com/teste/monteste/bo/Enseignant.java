package com.teste.monteste.bo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Enseignant extends Personnel {
    public String  Specialite;
    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;
    @ManyToMany
    @JoinTable(
            name = "enseignant_filiere",
            joinColumns = @JoinColumn(name = "enseignant_id"),
            inverseJoinColumns = @JoinColumn(name = "filiere_id")
    )
    private Set<Filiere> filieres = new HashSet<>();
    @OneToMany(mappedBy = "enseignant")
    private Set<EnseignantModuleFiliereNiveau> affectations = new HashSet<>();
    @OneToMany(mappedBy = "enseignant")
    private Set<ElementPedagogique> elementPedagogiques = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name = "groupe_id")
    private Groupe groupe;


    public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public String getSpecialite() {
        return Specialite;
    }

    public void setSpecialite(String specialite) {
        Specialite = specialite;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }


    public Set<ElementPedagogique> getElementPedagogiques() {
        return elementPedagogiques;
    }

    public void setElementPedagogiques(Set<ElementPedagogique> elementPedagogiques) {
        this.elementPedagogiques = elementPedagogiques;
    }

    public Set<Filiere> getFilieres() {
        return filieres;
    }

    public void setFilieres(Set<Filiere> filieres) {
        this.filieres = filieres;
    }

    public Set<EnseignantModuleFiliereNiveau> getAffectations() {
        return affectations;
    }

    public void setAffectations(Set<EnseignantModuleFiliereNiveau> affectations) {
        this.affectations = affectations;
    }
}