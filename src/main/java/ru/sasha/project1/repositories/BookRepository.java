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
public interface BookRepository extends JpaRepository<Book, Integer> {

    //UPDATE book SET owner_id=? WHERE id=?
    @Modifying
    @Query("UPDATE Book b SET b.owner_id = :owner_id where b.id =:id")
    void updateBookOwner(@Param("owner_id") Person owner_id, @Param("id") int id);

    List<Book> findByTitleStartingWith(String title);
}
