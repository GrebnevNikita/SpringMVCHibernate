package com.journaldev.spring.service;

import java.util.List;

import com.journaldev.spring.model.Person;
import org.springframework.transaction.annotation.Transactional;

public interface PersonService {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons(int pageid,String pst);

    @Transactional
    List<Integer> getPageList(int pageid,String pst);
	public void create10random();
	public void deleteAll();
    public Person getPersonById(int id);
	public void removePerson(int id);
	
}
