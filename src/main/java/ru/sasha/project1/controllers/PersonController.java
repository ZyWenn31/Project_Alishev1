package ru.sasha.project1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sasha.project1.dao.PersonDAO;
import ru.sasha.project1.models.Person;
import ru.sasha.project1.services.PersonService;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    // Список всех читателей
    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personService.findAll());
        return "people/index";
    }

    // Информация о конкретном читателе
    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personService.findOne(id));
        // список книг читателя
        model.addAttribute("personBooks", personService.showPersonBooks(personService.findOne(id)));
        return "people/personInfo";
    }

    // Вызов представления для создания читателя
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){return "people/new";}

    // Создание читателя
    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "people/new";
        }
        personService.save(person);
        return "redirect:/people";
    }

    //Редактирование существующего пользователя
    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personService.findOne(id));
        return "people/edit";
    }

    // -|-
    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                               @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "people/edit";
        }
        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson (@PathVariable("id") int id){
        personService.delete(id);
        return "redirect:/people";
    }
}