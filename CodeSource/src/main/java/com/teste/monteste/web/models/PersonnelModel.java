package com.teste.monteste.web.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class PersonnelModel {

	public static final int TYPE_ADMIN = 1;
	public static final int TYPE_ENSEIGNANT = 2;


	
	private Long idPersonne;

	@NotBlank(message = "This field is required")
	private String nom;

	@NotBlank(message = "This field is required")
	private String prenom;
	
	@NotBlank(message = "This field is required")
	private String cin;

	private String cne;
	
	@NotEmpty
	private String email;


	private int typePerson;
	
	
	public PersonnelModel() {
		
	}

	public PersonnelModel(int typePerson) {
		this.typePerson = typePerson;
	}




	public Long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
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

	public int getTypePerson() {
		return typePerson;
	}

	public void setTypePerson(int typePerson) {
		this.typePerson = typePerson;
	}


	public static int getTypeAdmin() {
		return TYPE_ADMIN;
	}
	
	public static int getTypeEnseignant() {
		return TYPE_ENSEIGNANT;
	}

	public String getCne() {
		return cne;
	}

	public void setCne(String cne) {
		this.cne = cne;
	}
	

	@Override
	public String toString() {
		return "PersonnelModel [idPersonne=" + idPersonne + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin
				+ ", cne=" + cne + ", email=" + email + ", typePerson=" + typePerson + "]";
	}
	
	

	
	
	

	

}
