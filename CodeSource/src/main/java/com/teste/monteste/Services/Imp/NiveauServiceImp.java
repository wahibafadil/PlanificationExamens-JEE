package com.teste.monteste.Services.Imp;

import com.teste.monteste.Services.INiveauService;
import com.teste.monteste.bo.Niveau;
import com.teste.monteste.dao.INiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class NiveauServiceImp implements INiveauService {

    @Autowired
    private INiveauRepository NiveauDao;
    @Override
    public void ajouter(Niveau n) {
        NiveauDao.save(n);
    }

    @Override
    public List<Niveau> all() {
        return NiveauDao.findAll();
    }

    @Override
    public Optional<Niveau> findById(Long id) {
        return NiveauDao.findById(id);
    }

    @Override
    public List<Niveau> FindAllById(List<Long> Ids) {
       return NiveauDao.findAllById(Ids);
    }
}
