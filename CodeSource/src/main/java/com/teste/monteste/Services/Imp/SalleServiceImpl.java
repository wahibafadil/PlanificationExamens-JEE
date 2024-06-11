package com.teste.monteste.Services.Imp;

import com.teste.monteste.bo.Salle;
import com.teste.monteste.dao.SalleRepository;
import com.teste.monteste.Services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalleServiceImpl implements SalleService {
    @Autowired
    private SalleRepository salleRepository;

    @Override
    public Salle saveSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    @Override
    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }

    @Override
    public Salle getSalleById(Long id) {
        return salleRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSalle(Long id) {
        salleRepository.deleteById(id);
    }

    // Other business logic methods
}
