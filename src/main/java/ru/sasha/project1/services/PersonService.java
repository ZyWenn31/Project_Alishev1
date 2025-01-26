package ru.sasha.project1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sasha.project1.models.Book;
import ru.sasha.project1.models.Person;
import ru.sasha.project1.repositories.PersonRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person){
        person.setId(id);
        personRepository.save(person);
    }

    @Transactional
    public void delete(int id){
        personRepository.deleteById(id);
    }

    public List<Person>findAll(){
        return personRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> person = personRepository.findById(id);
        return  person.orElse(null);
    }

    public List<Book> showPersonBooks(Person owner_id){
        List<Book> books = personRepository.findAllBooksOfReader(owner_id);
        for(Book book : books){
            if ((new Date().getTime() - book.getDate_of_take().getTime())/86400000 > 10){
                book.setOverdue(true);
            }
        }

        return personRepository.findAllBooksOfReader(owner_id);
    }
}
