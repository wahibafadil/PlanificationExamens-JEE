package com.teste.monteste.dao;

import com.teste.monteste.bo.PersonnelAdministratif;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonnelAdministratifRepository extends JpaRepository<PersonnelAdministratif,Long> {
}
