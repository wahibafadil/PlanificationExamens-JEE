package com.teste.monteste.Services;

import com.teste.monteste.bo.Enseignant;

import java.util.List;

public interface IEnseignantService {
    public void ajouter(Enseignant e);
    public List<Enseignant>all();
	public Object getAllEnseignants();
	public Enseignant getEnseignantById(Long enseignantId);
}