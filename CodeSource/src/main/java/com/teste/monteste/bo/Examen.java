package com.teste.monteste.bo;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Examen {




	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String semestre;
    private String session;
    private String type;
    private String date;
    private String heureDebut;
    private int dureePrevue;
    private int dureeReelle;
    private String anneeUniversitaire;
    private String rapportTextuel;
    private String epreuvePath;
    private String pvPath;

    @ManyToOne
    @JoinColumn(name = "coordonnateur_id")
    private Enseignant coordonnateur;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private ElementPedagogique module;
    
    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;

    

    @ManyToMany
    @JoinTable(
            name = "examen_surveillant",
            joinColumns = @JoinColumn(name = "examen_id"),
            inverseJoinColumns = @JoinColumn(name = "surveillant_id")
    )
    private Set<Enseignant> surveillants = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "examen_controleur_absence",
            joinColumns = @JoinColumn(name = "examen_id"),
            inverseJoinColumns = @JoinColumn(name = "controleur_id")
    )
    private Set<PersonnelAdministratif> controleursAbsence = new HashSet<>();

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getDureePrevue() {
        return dureePrevue;
    }

    public void setDureePrevue(int dureePrevue) {
        this.dureePrevue = dureePrevue;
    }

    public int getDureeReelle() {
        return dureeReelle;
    }

    public void setDureeReelle(int dureeReelle) {
        this.dureeReelle = dureeReelle;
    }

    public String getAnneeUniversitaire() {
        return anneeUniversitaire;
    }

    public void setAnneeUniversitaire(String anneeUniversitaire) {
        this.anneeUniversitaire = anneeUniversitaire;
    }

    public String getRapportTextuel() {
        return rapportTextuel;
    }

    public void setRapportTextuel(String rapportTextuel) {
        this.rapportTextuel = rapportTextuel;
    }

    public String getEpreuvePath() {
        return epreuvePath;
    }

    public void setEpreuvePath(String epreuvePath) {
        this.epreuvePath = epreuvePath;
    }

    public String getPvPath() {
        return pvPath;
    }

    public void setPvPath(String pvPath) {
        this.pvPath = pvPath;
    }

    public Enseignant getCoordonnateur() {
        return coordonnateur;
    }

    public void setCoordonnateur(Enseignant coordonnateur) {
        this.coordonnateur = coordonnateur;
    }

    public ElementPedagogique getModule() {
        return module;
    }

    public void setModule(ElementPedagogique module) {
        this.module = module;
    }

  
    public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Set<Enseignant> getSurveillants() {
        return surveillants;
    }

    public void setSurveillants(Set<Enseignant> surveillants) {
        this.surveillants = surveillants;
    }

    public Set<PersonnelAdministratif> getControleursAbsence() {
        return controleursAbsence;
    }

    public void setControleursAbsence(Set<PersonnelAdministratif> controleursAbsence) {
        this.controleursAbsence = controleursAbsence;
    }
}

