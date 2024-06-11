package com.teste.monteste.Services;

import java.util.List;


import com.teste.monteste.bo.Compte;
import com.teste.monteste.bo.Role;
import com.teste.monteste.utils.ExclExporter;


public interface ICompteService {
	
	public List<Role> getAllRoles();
	
	public List<Compte> getAllAccounts();
	
	public Compte getAccountByUserName(String login);
	
	public String createUser(Long idRole, Long idPerson);
	
	public ExclExporter prepareCompteExport(List<Compte> comptes) ;
}
