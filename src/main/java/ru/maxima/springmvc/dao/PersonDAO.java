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
        people.add(new Person(++PEOPLE_COUNT, "Viktor", 20, "mail@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Vasiliy", 25, "maixzcczxcl@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Nikolay", 30, "masfdsfdsfil@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Konstantin", 15, "123123123mail@mail.ru"));
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

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
