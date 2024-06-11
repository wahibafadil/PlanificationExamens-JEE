package com.teste.monteste.Services;

import com.teste.monteste.bo.PersonnelAdministratif;

import java.util.List;

public interface IPersonnelAdministratifService {
    public void ajouter(PersonnelAdministratif p);
    public List<PersonnelAdministratif> all();
}
