package com.teste.monteste.Services.Imp;

import com.teste.monteste.bo.Examen;
import com.teste.monteste.dao.ExamenRepository;
import com.teste.monteste.Services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamenServiceImpl implements ExamenService {
    @Autowired
    private ExamenRepository examenRepository;

    @Override
    public Examen saveExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public List<Examen> getAllExamens() {
        return examenRepository.findAll();
    }

    @Override
    public Examen getExamenById(Long id) {
        return examenRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteExamen(Long id) {
        examenRepository.deleteById(id);
    }

    // Other business logic methods
}
