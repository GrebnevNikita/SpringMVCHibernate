package com.journaldev.spring.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {

    private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void addPerson(Person p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);

    }

    @Override
    public void updatePerson(Person p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
        logger.info("Person updated successfully, Person Details=" + p);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Person> listPersons(int pageid, String pst) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Person> personsList = session.createQuery("from Person").list();
        List<Person> personsListPaged = new ArrayList<Person>();
        List<Person> personsListSorted = new ArrayList<Person>();

        if (!pst.equals("nosearch")) {
            for (Person p : personsList
                    ) {
                if (p.toString().contains(pst)) {
                    personsListSorted.add(p);
                }
            }
            personsList = personsListSorted;
        }

        int maxPerPage = 10;
        int totalPageCount = 0;
        if (pageid == 0) {
            pageid = 1;
        }
        if (personsList.size() % maxPerPage == 0) {
            totalPageCount = personsList.size() / maxPerPage;
        } else {
            totalPageCount = personsList.size() / maxPerPage;
            totalPageCount++;
        }
        if (totalPageCount < pageid) {
            pageid = totalPageCount;
        }
        if (personsList.size() < 1) {

        } else {

            for (int i = (pageid - 1) * maxPerPage; i < (pageid - 1) * maxPerPage + 10; i++) {
                if (i >= personsList.size()) {
                    break;
                }
                personsListPaged.add(personsList.get(i));
            }
        }
        return personsListPaged;
    }


    @Override
    public Person getPersonById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person p = (Person) session.load(Person.class, new Integer(id));
        logger.info("Person loaded successfully, Person details=" + p);
        return p;
    }

    @Override
    public void create10random() {

        Session session = this.sessionFactory.getCurrentSession();
        for (int i = 0; i < 10; i++) {

            Person person = new Person();
            boolean isAdmin = false;
            if (Math.random() > 0.5) {
                isAdmin = true;
            }
            int age = (int) (Math.random() * 100);
            char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int j = 0; j < Math.random() * 20; j++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            String output = sb.toString();


            person.setAdmin(isAdmin);
            person.setAge(age);
            person.setName(output);
            session.persist(person);
        }
    }

    @Override
    public void deleteAll() {
        Session session = this.sessionFactory.getCurrentSession();
        String stringQuery = "DELETE FROM Person";
        Query query = session.createQuery(stringQuery);
        query.executeUpdate();

    }

    @Override
    public List<Integer> getPageList(int pageid, String pst) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Person> personsList = session.createQuery("from Person").list();
        int maxPerPage = 10;
        int totalPageCount = 0;
        List<Person> personsListSorted = new ArrayList<Person>();
        if (!pst.equals("nosearch")) {
            for (Person p : personsList
                    ) {
                if (p.toString().contains(pst)) {
                    personsListSorted.add(p);
                }
            }
            personsList = personsListSorted;
        }

        if (pageid == 0) {
            pageid = 1;
        }
        if (personsList.size() % maxPerPage == 0) {
            totalPageCount = personsList.size() / maxPerPage;
        } else {
            totalPageCount = personsList.size() / maxPerPage;
            totalPageCount++;
        }
        List<Integer> pageCount = new ArrayList<Integer>();
        for (int i = 1; i <= totalPageCount; i++) {
            pageCount.add(i);

        }
        return pageCount;
    }

    @Override
    public void removePerson(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person p = (Person) session.load(Person.class, new Integer(id));
        if (null != p) {
            session.delete(p);
        }
        logger.info("Person deleted successfully, person details=" + p);
    }

}
