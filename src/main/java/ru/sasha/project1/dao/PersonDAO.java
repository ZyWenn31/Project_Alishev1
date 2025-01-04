package ru.sasha.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sasha.project1.models.Book;
import ru.sasha.project1.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public List<Book> showPersonBooks(int id){
        return jdbcTemplate.query("SELECT * FROM book INNER JOIN person ON book.owner_id = person.id WHERE owner_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person(name, born) VALUES (?, ?)", person.getName(), person.getBorn());
    }

    public void update(int id, Person person){
        jdbcTemplate.update("UPDATE person SET name=?, born=? WHERE id=?", person.getName(), person.getBorn(), id);
    }

    public void delete(int id) {jdbcTemplate.update("DELETE FROM person WHERE id=?", id);}



}

// Переопределить метод update (Разделить обновление книги и добавление владельца), также добавить метод (мб join) для отображения всех книг пользователя
