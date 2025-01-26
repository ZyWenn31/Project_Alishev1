package ru.sasha.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sasha.project1.models.Book;
import ru.sasha.project1.models.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    //SELECT * FROM book INNER JOIN person ON book.owner_id = person.id WHERE owner_id=?
    @Modifying
    @Query("SELECT b FROM Book b WHERE b.owner_id=:owner_id")
    List<Book> findAllBooksOfReader(@Param("owner_id") Person owner_id);

}
