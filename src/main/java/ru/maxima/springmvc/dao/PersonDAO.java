package ru.maxima.springmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.springmvc.models.Person;


import java.sql.*;
import java.util.*;

@Component
public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO( SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
       return session.createQuery("select p from Person p ", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());
        person.setEmail(updatedPerson.getEmail());
        person.setAdmin(updatedPerson.isAdmin());

        session.update(person);
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }

    public void makeAdmin(Person person) {
//        person.setAdmin(true);
//        jdbcTemplate.update("update person set is_admin = ? where id = ?",
//                person.isAdmin(), person.getId());
    }

    public void testWithoutBatch() {
//        long start = System.currentTimeMillis();
//
//        List<Person> people = create1000person();
//        for (Person person : people) {
//            jdbcTemplate.update("insert into person values(?, ?, ?, ?)", person.getId(), person.getName(),
//                    person.getAge(), person.getEmail());
//        }
//
//        long end = System.currentTimeMillis();
//
//        System.out.println("Without BatchUpdate - " + (end - start));
    }


    public void testWithBatch() {
//        long start = System.currentTimeMillis();
//        List<Person> people = create1000person();
//
//        jdbcTemplate.batchUpdate("insert into person values(?, ?, ?, ?)", new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setInt(1, people.get(i).getId());
//                ps.setString(2, people.get(i).getName());
//                ps.setInt(3, people.get(i).getAge());
//                ps.setString(4, people.get(i).getEmail());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return people.size();
//            }
//        });
//
//        long end = System.currentTimeMillis();
//
//        System.out.println("With BatchUpdate - " + (end - start));

    }

    public List<Person> create1000person() {
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "Name" + i,  i, "test" + i + "@mail.ru"));
        }
        return people;
    }


}
