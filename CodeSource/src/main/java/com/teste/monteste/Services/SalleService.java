package com.teste.monteste.Services;

import com.teste.monteste.bo.Salle;
import java.util.List;

public interface SalleService {
    Salle saveSalle(Salle salle);
    List<Salle> getAllSalles();
    Salle getSalleById(Long id);
    void deleteSalle(Long id);
}
