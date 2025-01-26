package ru.sasha.project1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sasha.project1.models.Book;
import ru.sasha.project1.models.Person;
import ru.sasha.project1.repositories.BookRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks(PageRequest pageRequest){
        return bookRepository.findAll(pageRequest).getContent();
    }

    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public List<Book> findAllBooks(Sort sort){
        return bookRepository.findAll(sort);
    }

    public Book findOne(int id){
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void save(Book book){
        book.setDate_of_take(new Date(2025));
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }

    @Transactional
    public void UpdateBookOwner(int id, Person owner_id, boolean take){
        if(take){
            bookRepository.findById(id).get().setDate_of_take(new Date());
        } else {
            bookRepository.findById(id).get().setDate_of_take(null);
        }
        bookRepository.updateBookOwner(owner_id, id);
    }

    public List<Book> SearchBookByTitle(String title){
        return bookRepository.findByTitleStartingWith(title);
    }

}
