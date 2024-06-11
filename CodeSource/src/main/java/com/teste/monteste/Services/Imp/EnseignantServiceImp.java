package com.teste.monteste.Services.Imp;

import com.teste.monteste.Services.IEnseignantService;
import com.teste.monteste.bo.Enseignant;
import com.teste.monteste.dao.IEnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnseignantServiceImp implements IEnseignantService {
    @Autowired
    private IEnseignantRepository enseignantRepository;

    @Override
    public void ajouter(Enseignant e) {
        enseignantRepository.save(e);
    }

    @Override
    public List<Enseignant> all() {
        return enseignantRepository.findAll();
    }

    @Override
    public Enseignant getEnseignantById(Long enseignantId) {
        return enseignantRepository.findById(enseignantId).orElse(null);
    }

    @Override
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }
}