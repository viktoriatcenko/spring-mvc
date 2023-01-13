package ru.maxima.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.maxima.springmvc.dao.PersonDAO;

@Controller
@RequestMapping("/people")
public class PeopleController {

    // PERSON - 1
    // PEOPLE - МНОГО

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        // Получение всех людей из нашей БД и пересылать их в отображение
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // Получать конкретного человека по его id
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }


}
