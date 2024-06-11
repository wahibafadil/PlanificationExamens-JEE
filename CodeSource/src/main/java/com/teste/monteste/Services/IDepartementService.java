package com.teste.monteste.Services;

import com.teste.monteste.bo.Departement;
import com.teste.monteste.bo.Filiere;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface IDepartementService {
    public void ajouter(Departement d);
    public List<Departement>all();
    public Optional<Departement> findById(Long id);
    public Map<Departement, Set<Filiere>> getDepartementsWithFilieres();


    }
