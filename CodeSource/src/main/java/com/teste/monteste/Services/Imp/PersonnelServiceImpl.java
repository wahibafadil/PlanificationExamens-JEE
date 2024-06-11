package com.teste.monteste.Services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teste.monteste.bo.Personnel;
import com.teste.monteste.dao.PersonnelRepository;
import com.teste.monteste.Services.IPersonnelService;
import com.teste.monteste.utils.ExclExporter;

@Service
@Transactional
public class PersonnelServiceImpl implements IPersonnelService {

	@Autowired
	private PersonnelRepository personDao;

	public List<Personnel> getAllPersonnes() {

		return personDao.findAll();
	}

	public void deletePersonne(Long id) {
		personDao.deleteById(id);

	}

	public Personnel getPersonneById(Long id) {
		return personDao.findById(id).get();

	}

	public void addPersonne(Personnel pPerson) {
		personDao.save(pPerson);

	}

	public void updatePersonne(Personnel pPerson) {
		personDao.save(pPerson);

	}

	public ExclExporter preparePersonneExport(List<Personnel> persons) {
		String[] columnNames = new String[] { "Nom", "Prénom", "CIN", "Email", "Télé" };
		String[][] data = new String[persons.size()][5];

		int i = 0;
		for (Personnel u : persons) {
			data[i][0] = u.getNom();
			data[i][1] = u.getPrenom();
			data[i][2] = u.getCin();
			data[i][3] = u.getEmail();
			i++;
		}

		return new ExclExporter(columnNames, data, "persons");

	}

	public Personnel getPersonneByCin(String cin) {

		return personDao.getPersonneByCin(cin);

	}

}
