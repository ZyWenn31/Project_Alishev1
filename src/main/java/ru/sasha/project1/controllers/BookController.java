package ru.sasha.project1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sasha.project1.dao.BookDAO;
import ru.sasha.project1.dao.PersonDAO;
import ru.sasha.project1.models.Book;
import ru.sasha.project1.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    // Список всех книг
    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    // Информация о конкретной книге
    @GetMapping("/{id}")
    public String showPerson(@ModelAttribute("person")Person person,@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("people", personDAO.index());
        return "books/bookInfo";
    }

    //Метод для изменения владельца книги
    @PatchMapping("/{id}/ownerEdit")
    public String editOwnerBook(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        bookDAO.updateOwner(id, person.getId());
        return "redirect:/books";
    }

    @PatchMapping("/{id}/ownerEditNull")
    public String editOwnerBookNull(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        bookDAO.updateOwner(id, null);
        return "redirect:/books";
    }

    // Вызов представления для создания читателя
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book){ System.out.println(555); return "books/new";}

    // Создание читателя
    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    //Редактирование Существующей книги
    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    // -|-
    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("person") @Valid Book book, BindingResult bindingResult,
                               @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook (@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
