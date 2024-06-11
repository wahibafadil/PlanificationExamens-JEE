package com.teste.monteste.Services.Imp;

import com.teste.monteste.bo.ElementPedagogique;
import com.teste.monteste.dao.IModuleRepository;
import com.teste.monteste.Services.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements IModuleService {
    @Autowired
    private IModuleRepository moduleRepository;

    @Override
    public List<ElementPedagogique> getAllModules() {
        return moduleRepository.findAll();
    }
}
