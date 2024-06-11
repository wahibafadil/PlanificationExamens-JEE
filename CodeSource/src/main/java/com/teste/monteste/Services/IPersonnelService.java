package com.teste.monteste.Services;

import java.util.List;

import com.teste.monteste.bo.Personnel;
import com.teste.monteste.utils.ExclExporter;

public interface IPersonnelService {

	public void addPersonne(Personnel pPerson);

	public void updatePersonne(Personnel pPerson);

	public List<Personnel> getAllPersonnes();

	public void deletePersonne(Long id);

	public Personnel getPersonneById(Long id);
	
	public Personnel getPersonneByCin(String cin);
	
	public ExclExporter preparePersonneExport(List<Personnel> persons);
	
	

}
