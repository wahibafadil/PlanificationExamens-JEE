package com.teste.monteste.dao;

import com.teste.monteste.bo.Personnel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
	
	Personnel getPersonneByCin(String cin);
    
}