package com.teste.monteste.dao;

import com.teste.monteste.bo.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
	public Compte getCompteByLogin(String username);
}
