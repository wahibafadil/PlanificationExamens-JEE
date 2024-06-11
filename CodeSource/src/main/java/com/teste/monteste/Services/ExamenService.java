package com.teste.monteste.Services;

import com.teste.monteste.bo.Examen;
import java.util.List;

public interface ExamenService {
    Examen saveExamen(Examen examen);
    List<Examen> getAllExamens();
    Examen getExamenById(Long id);
    void deleteExamen(Long id);
}
