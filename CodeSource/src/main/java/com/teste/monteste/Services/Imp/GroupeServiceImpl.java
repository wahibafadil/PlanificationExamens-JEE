package com.teste.monteste.Services.Imp;

import com.teste.monteste.bo.Groupe;
import com.teste.monteste.dao.GroupeRepository;
import com.teste.monteste.Services.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupeServiceImpl implements GroupeService {

    @Autowired
    private GroupeRepository groupeRepository;

    @Override
    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAll();
    }

    @Override
    public Groupe getGroupeById(Long id) {
        return groupeRepository.findById(id).orElse(null);
    }

    @Override
    public void saveGroupe(Groupe groupe) {
        groupeRepository.save(groupe);
    }
}
