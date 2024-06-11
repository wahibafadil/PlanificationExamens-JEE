package com.teste.monteste.Services.Imp;

import com.teste.monteste.Services.IDepartementService;
import com.teste.monteste.bo.Departement;
import com.teste.monteste.bo.Filiere;
import com.teste.monteste.dao.IDepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartementServiceImp implements IDepartementService {

    @Autowired
    private IDepartementRepository DepartementDao;

    @Override
    public void ajouter(Departement d) {
        DepartementDao.save(d);
    }

    @Override
    public List<Departement> all() {
        return DepartementDao.findAll();
    }

    @Override
    public Optional<Departement> findById(Long id) {
        return DepartementDao.findById(id);
    }

    @Override
    public Map<Departement,Set<Filiere>> getDepartementsWithFilieres() {
        List<Departement> departements = DepartementDao.findAll();

        Map<Departement, Set<Filiere>> departementsWithFilieres = departements.stream()
                .collect(Collectors.toMap(departement -> departement, Departement::getFilieres));

            return departementsWithFilieres;
    }

}