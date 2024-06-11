package com.teste.monteste.dao;

import com.teste.monteste.bo.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartementRepository extends JpaRepository<Departement,Long> {
}
