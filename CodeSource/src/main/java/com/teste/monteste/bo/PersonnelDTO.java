package com.teste.monteste.bo;

import jakarta.persistence.Column;

import java.util.List;
import java.util.Set;

public class PersonnelDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String type;
    private String specialite;
    @Column(unique = true)
    private String cin;

    private String email;
    private  String photo;
    private  Departement departement;
    private Set<Filiere> filiers;



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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Set<Filiere> getFiliers() {
        return filiers;
    }

    public void setFiliers(Set<Filiere> filiers) {
        this.filiers = filiers;
    }

    @Override
    public String toString() {
        return "PersonnelDTO{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", type='" + type + '\'' +
                ", specialite='" + specialite + '\'' +
                ", cin='" + cin + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", departement=" + departement +
                ", filiers=" + filiers +
                '}';
    }
}

