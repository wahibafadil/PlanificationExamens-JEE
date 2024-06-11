package com.teste.monteste.Services;

import com.teste.monteste.bo.Niveau;

import java.util.List;
import java.util.Optional;

public interface INiveauService {
public void ajouter(Niveau n);
public List<Niveau> all();
public Optional<Niveau>findById(Long id);
public List<Niveau>FindAllById(List<Long> Ids);

}
