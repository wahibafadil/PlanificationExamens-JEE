package com.teste.monteste.Services;

import com.teste.monteste.bo.Groupe;

import java.util.List;

public interface GroupeService {
    List<Groupe> getAllGroupes();
    void saveGroupe(Groupe groupe);
	Groupe getGroupeById(Long id);
}
