package com.teste.monteste.Services.Imp;

import java.util.List;

import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teste.monteste.bo.Personnel;
import com.teste.monteste.bo.Role;
import com.teste.monteste.bo.Compte;
import com.teste.monteste.dao.PersonnelRepository;
import com.teste.monteste.dao.RoleRepository;
import com.teste.monteste.dao.CompteRepository;
import com.teste.monteste.Services.ICompteService;
import com.teste.monteste.utils.ExclExporter;

@Service
@Transactional
public class CompteServiceImp implements ICompteService {

	@Autowired
	private CompteRepository userDao;

	@Autowired
	private RoleRepository roleDao;

	@Autowired
	private PersonnelRepository personDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Role> getAllRoles() {
		return roleDao.findAll();
	}

	public List<Compte> getAllAccounts() {
		return userDao.findAll();
	}

	public String createUser(Long idRole, Long idPerson) {

		// récupérer la personne de la base de données
		Personnel person = personDao.findById(idPerson).get();

		// Créer le compte
		Compte userAccount = new Compte();

		// determiner la personne
		userAccount.setProprietaire(person);

		// Affecter le role
		userAccount.setRole(roleDao.findById(idRole).get());

		// génrer le mot de passe aléatoirement
		String generatedPass = generatePassayPassword();

		// hachage du mot de passe + gain de sel
		String encodedPass = passwordEncoder.encode(generatedPass);

		// affecter ce mot de passe
		userAccount.setPassword(encodedPass);

		// On construit un login de type "nom+prenom " s'il est dispo
		String login = person.getNom() + person.getPrenom();

		Compte account = userDao.getCompteByLogin(login);

		if (account == null) {

			userAccount.setLogin(login);

			// Créer le compte
			userDao.save(userAccount);
			return generatedPass;
		}

		int i = 0;

		// sinon, on cherche un login de type nom+prenom+"_"+ entier
		while (true) {

			login = person.getNom() + person.getPrenom() + "_" + i;
			account = userDao.getCompteByLogin(login);
			if (account == null) {
				userAccount.setLogin(login);

				// Créer le compte
				userDao.save(userAccount);
				return generatedPass;
			}

			i++;
		}
	}

	public ExclExporter prepareCompteExport(List<Compte> comptes) {
		String[] columnNames = new String[] { "Login", "Rôle", "Nom & Prénom" };
		String[][] data = new String[comptes.size()][3];

		int i = 0;
		for (Compte u : comptes) {
			data[i][0] = u.getLogin();
			data[i][1] = u.getRole().getNomRole();
			data[i][2] = u.getProprietaire().getNom() + " " + u.getProprietaire().getPrenom();
			i++;
		}

		return new ExclExporter(columnNames, data, "comptes");

	}

	// génère le mot de passe. Il se base sur Passay
	public String generatePassayPassword() {
		CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);

		PasswordGenerator passwordGenerator = new PasswordGenerator();
		String password = passwordGenerator.generatePassword(10, digits);

		return password;
	}

	@Override
	public Compte getAccountByUserName(String login) {
	

		return userDao.getCompteByLogin(login);
	}

}
