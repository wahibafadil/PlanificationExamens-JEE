package com.teste.monteste.Services;

import com.teste.monteste.bo.Filiere;
import com.teste.monteste.bo.Niveau;

import java.util.List;
import java.util.Optional;

public interface IFiliereService {
    public void ajouter(Filiere f);
    public List<Filiere> all();
    public Optional<Filiere> findById(Long id);
    public List<Filiere>FindAllById(List<Long> Ids);

}
