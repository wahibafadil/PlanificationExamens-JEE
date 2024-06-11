package com.teste.monteste.bo;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGroupe;
    private String nomGroupe;

    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enseignant> enseignants = new HashSet<>();

    @Transient
    private Set<Long> enseignantIds = new HashSet<>();

    public Long getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(Long idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public Set<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(Set<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public Set<Long> getEnseignantIds() {
        return enseignantIds;
    }

    public void setEnseignantIds(Set<Long> enseignantIds) {
        this.enseignantIds = enseignantIds;
    }
}
