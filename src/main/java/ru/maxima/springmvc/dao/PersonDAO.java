package ru.maxima.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.maxima.springmvc.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private int PEOPLE_COUNT;
    private List<Person> people;

    public PersonDAO() {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Viktor"));
        people.add(new Person(++PEOPLE_COUNT, "Vasiliy"));
        people.add(new Person(++PEOPLE_COUNT, "Nikolay"));
        people.add(new Person(++PEOPLE_COUNT, "Konstantin"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream()
                .filter(p -> p.getId() == id)
                .findAny()
                .orElse(null);
    }
}
