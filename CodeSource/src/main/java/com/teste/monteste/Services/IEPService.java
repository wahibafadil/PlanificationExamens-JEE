package com.teste.monteste.Services;

import com.teste.monteste.bo.ElementPedagogique;
import com.teste.monteste.bo.Filiere;
import com.teste.monteste.bo.Niveau;

import java.util.List;
import java.util.Map;

public interface IEPService {
    public void ajouter(ElementPedagogique M);

    public List<ElementPedagogique> all();

    public Map<Filiere, List<Niveau>> getFilieresWithNiveaux();

    public List<ElementPedagogique> findModulesByFiliereAndNiveau(Long filiereId, Long niveauId);


}