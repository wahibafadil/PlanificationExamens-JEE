package com.teste.monteste.bo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String Description;
    private Date date_Ajout;
    private String abreviation;


    @OneToMany(mappedBy = "departement")
    private Set<Filiere> filieres;


    @OneToMany(mappedBy = "departement")
    private Set<Enseignant> professeurs;

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

    public Set<Filiere> getFilieres() {
        return filieres;
    }

    public void setFilieres(Set<Filiere> filieres) {
        this.filieres = filieres;
    }

    public Set<Enseignant> getProfesseurs() {
        return professeurs;
    }

    public void setProfesseurs(Set<Enseignant> professeurs) {
        this.professeurs = professeurs;
    }

    public Date getDate_Ajout() {
        return date_Ajout;
    }

    public void setDate_Ajout(Date date_Ajout) {
        this.date_Ajout = date_Ajout;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", Description='" + Description + '\'' +
                ", date_Ajout=" + date_Ajout +
                ", abreviation='" + abreviation + '\'' +
                ", filieres=" + filieres +
                ", professeurs=" + professeurs +
                '}';
    }
}
