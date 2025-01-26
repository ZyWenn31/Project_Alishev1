package ru.sasha.project1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sasha.project1.models.Book;
import ru.sasha.project1.models.Person;
import ru.sasha.project1.services.BookService;
import ru.sasha.project1.services.PersonService;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    //Страница поиска книг
    @GetMapping("/search")
    public String searchBooks(){
        return "books/searchBookFirst";
    }

    //Найденная книга
    @GetMapping("/searched")
    public String searchedBooks(@RequestParam("title") String title, Model model){
        model.addAttribute("book", bookService.SearchBookByTitle(title));
        return "books/SearchedBook";
    }

    // Список всех книг
    @GetMapping()
    public String index(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                        @RequestParam(value = "size", required = false) Integer size,
                        @RequestParam(value = "sorting", required = false) boolean sorting, Model model){
        if(size == null){
            if(sorting){
                model.addAttribute("books", bookService.findAllBooks(Sort.by("year")));
            } else {
                model.addAttribute("books", bookService.findAllBooks());
            }
        }else{
            if(sorting){
                model.addAttribute("books", bookService.findAllBooks(PageRequest.of(page, size, Sort.by("year"))));
            } else {
                model.addAttribute("books", bookService.findAllBooks(PageRequest.of(page, size)));
            }
        }
        return "books/index";
    }

    // Информация о конкретной книге
    @GetMapping("/{id}")
    public String showPerson(@ModelAttribute("person")Person person,@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.findOne(id));
        model.addAttribute("people", personService.findAll());
        return "books/bookInfo";
    }

    //Метод для изменения владельца книги
    @PatchMapping("/{id}/ownerEdit")
    public String editOwnerBook(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        bookService.UpdateBookOwner(id, person, true);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/ownerEditNull")
    public String editOwnerBookNull(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        bookService.UpdateBookOwner(id, null, false);
        return "redirect:/books";
    }

    // Вызов представления для создания читателя
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book){  return "books/new";}

    // Создание читателя
    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/new";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    //Редактирование Существующей книги
    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";
    }

    // -|-
    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("person") @Valid Book book, BindingResult bindingResult,
                               @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "books/edit";
        }
        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook (@PathVariable("id") int id){
        bookService.delete(id);
        return "redirect:/books";
    }
}
