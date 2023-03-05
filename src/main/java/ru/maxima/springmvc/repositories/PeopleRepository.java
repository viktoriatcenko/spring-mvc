package ru.maxima.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.springmvc.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
