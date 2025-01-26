package ru.sasha.project1.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sasha.project1.models.Book;

import java.util.List;

@Component
public class BookDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index(){
//        List<Book>query = jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
//        return query;
//    }
//
//    public Book show(int id){
//        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
//    }
//
//    public void save (Book book){
//        jdbcTemplate.update("INSERT INTO book(title, author, year) VALUES (?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYear());
//    }
//
//    public void update(int id, Book book){
//        jdbcTemplate.update("UPDATE book SET title=?, author=?, year=? WHERE id=?", book.getTitle(), book.getAuthor(), book.getYear(), id);
//    }
//
//    public void updateOwner(int id, Integer owner_id){
//        jdbcTemplate.update("UPDATE book SET owner_id=? WHERE id=?", owner_id, id);
//    }
//
//    public void delete(int id){
//        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
//    }
}
