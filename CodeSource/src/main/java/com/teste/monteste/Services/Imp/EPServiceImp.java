package com.teste.monteste.Services.Imp;

import com.teste.monteste.Services.IEPService;
import com.teste.monteste.bo.Filiere;
import com.teste.monteste.bo.Niveau;
import com.teste.monteste.dao.IFiliereRepository;
import com.teste.monteste.dao.IModuleRepository;
import com.teste.monteste.dao.INiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.teste.monteste.bo.ElementPedagogique;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class EPServiceImp implements IEPService {
    @Autowired
    private IModuleRepository ModuleDao;
    @Autowired

    private IFiliereRepository FiliereDao;
    @Autowired
    private INiveauRepository NiveauDao;


    @Override
    public void ajouter(ElementPedagogique M) {
        ModuleDao.save(M);
    }

    @Override
    public List<ElementPedagogique> all() {
        return ModuleDao.findAll();
    }

    @Override
    public Map<Filiere, List<Niveau>> getFilieresWithNiveaux() {
        Map<Filiere, List<Niveau>> filieresWithNiveaux = new HashMap<>();

        List<Filiere> filieres = FiliereDao.findAll();
        for (Filiere filiere : filieres) {
            List<Niveau> niveaux = NiveauDao.findAll().stream()
                    .filter(niveau -> niveau.getFiliere().equals(filiere))
                    .collect(Collectors.toList());
            filieresWithNiveaux.put(filiere, niveaux);
        }

        return filieresWithNiveaux;
    }

    public List<ElementPedagogique> findModulesByFiliereAndNiveau(Long filiereId, Long niveauId) {
        return ModuleDao.findModulesByFiliereAndNiveau(filiereId, niveauId);
    }

}