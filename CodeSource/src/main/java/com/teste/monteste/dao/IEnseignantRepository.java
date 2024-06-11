package com.teste.monteste.dao;

import com.teste.monteste.bo.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnseignantRepository extends JpaRepository<Enseignant,Long> {
}
