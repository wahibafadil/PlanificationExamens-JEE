package com.teste.monteste.Services.Imp;

import com.teste.monteste.Services.IFiliereService;
import com.teste.monteste.bo.Filiere;
import com.teste.monteste.dao.IFiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FiliereServiceImp implements IFiliereService {
    @Autowired
    private IFiliereRepository FiliereDao;
    @Override
    public void ajouter(Filiere f) {

        FiliereDao.save(f);
    }

    @Override
    public List<Filiere> all() {
        return FiliereDao.findAll();
    }

    @Override
    public Optional<Filiere> findById(Long id) {
        return FiliereDao.findById(id);
    }



    @Override
    public List<Filiere> FindAllById(List<Long> Ids) {
        return FiliereDao.findAllById(Ids);
    }


}
