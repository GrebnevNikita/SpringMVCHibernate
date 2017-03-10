package com.journaldev.spring.dao;

import java.util.List;

import com.journaldev.spring.model.Person;

public interface PersonDAO {



//	CREATE TABLE `Person` (
//			`id` int(8) unsigned NOT NULL AUTO_INCREMENT,
//  `name` varchar(25) NOT NULL DEFAULT '',
//	`age` int(3) unsigned NOT NULL,
//			`admin` BOOLEAN NOT NULL,
//			`date` TIMESTAMP NOT NULL,
//
//	PRIMARY KEY (`id`)
//) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons(int pageid,String pst);
	public Person getPersonById(int id);
	public void create10random();
	public void deleteAll();


	public void removePerson(int id);

	public List<Integer> getPageList(int pageid,String pst);
}
