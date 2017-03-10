package com.journaldev.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journaldev.spring.dao.PersonDAO;
import com.journaldev.spring.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	private PersonDAO personDAO;

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	@Transactional
	public void addPerson(Person p) {
		this.personDAO.addPerson(p);
	}

	@Override
	@Transactional
	public void updatePerson(Person p) {
		this.personDAO.updatePerson(p);
	}

	@Override
	@Transactional
	public List<Person> listPersons(int pageid,String pst) {
		return this.personDAO.listPersons(pageid, pst);
	}
	@Override
	@Transactional
	public  List<Integer> getPageList(int pageid,String pst) {
		return this.personDAO.getPageList(pageid,pst);
	}

	@Override
	@Transactional
	public void create10random()
		{
			this.personDAO.create10random();
	}

	@Override
	@Transactional
	public void deleteAll() {
		this.personDAO.deleteAll();
	}

	@Override
	@Transactional
	public Person getPersonById(int id) {
		return this.personDAO.getPersonById(id);
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		this.personDAO.removePerson(id);
	}

}
