package com.springcourse.controllers;

import com.springcourse.dao.PersonDAO;
import com.springcourse.modules.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        //получим всех людей из ДАО и передадим на отображение в представление
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // получим одного человека по id и передадим на отображение в представление

        model.addAttribute("person", personDAO.show(id));

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {

        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {

        personDAO.save(person);
        return "redirect:/people";
    }
}