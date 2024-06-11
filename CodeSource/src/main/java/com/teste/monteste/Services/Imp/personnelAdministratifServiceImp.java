package com.teste.monteste.Services.Imp;

import com.teste.monteste.Services.IPersonnelAdministratifService;
import com.teste.monteste.bo.PersonnelAdministratif;
import com.teste.monteste.dao.IPersonnelAdministratifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class personnelAdministratifServiceImp implements IPersonnelAdministratifService {
    @Autowired
    private IPersonnelAdministratifRepository PersonnelAdministratifDao;

    @Override
    public void ajouter(PersonnelAdministratif p) {
     PersonnelAdministratifDao.save(p);

    }

    @Override
    public List<PersonnelAdministratif> all() {
        return PersonnelAdministratifDao.findAll();
    }
}
