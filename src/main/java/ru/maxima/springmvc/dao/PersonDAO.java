package ru.maxima.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.maxima.springmvc.models.Person;

import java.sql.*;
import java.util.*;

@Component
public class PersonDAO {
    private int PEOPLE_COUNT;
    private final String URL = "jdbc:postgresql://localhost:5432/my_db";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";

    private Connection connection;

    public PersonDAO() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "select * from person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public Person show(int id) {
        Person person = new Person();
        try {
            Statement statement = connection.createStatement();
            String SQL = "select * from person where id = " + id;
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public void save(Person person) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "insert into person values (" + ++PEOPLE_COUNT + ", '" + person.getName()
                    + "', " + person.getAge() + ", '" + person.getEmail() + "')";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person updatedPerson) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "update person set name = '" + updatedPerson.getName()
                    + "', age = " + updatedPerson.getAge() + ", email = '" + updatedPerson.getEmail() +
                    "' where id = " + id;
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "delete from person where id = " + id;
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
