package ru.sasha.project1.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "The title should not be empty")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "The author should not be empty")
    @Size(min = 0, max = 50, message = "The author should be maximum 50 characters")
    @Column(name = "author")
    private String author;

    @Min(value = 1855, message = "The year should be minimum 1855")
    @Max(value = 2024, message = "The year should be maximum 2024")
    @Column(name = "year")
    private int year;


    @Column(name = "date_of_take")
    @Temporal(TemporalType.DATE)
    private Date date_of_take;

    @Transient
    private boolean Overdue = false;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Person owner_id;

    public Book(String title, String author, int year, Person owner_id) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.owner_id = owner_id;
    }

    public boolean isOverdue() {
        return Overdue;
    }

    public void setOverdue(boolean overdue) {
        Overdue = overdue;
    }

    public Person getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Person owner_id) {
        this.owner_id = owner_id;
    }

    public Book(){}

    public Date getDate_of_take() {
        return date_of_take;
    }

    public void setDate_of_take(Date date_of_take) {
        this.date_of_take = date_of_take;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getTitle() {
        return title;
    }

    public void setTitle( String title) {
        this.title = title;
    }

    public  String getAuthor() {
        return author;
    }

    public void setAuthor( String author) {
        this.author = author;
    }


    public int getYear() {
        return year;
    }

    public void setYear( int year) {
        this.year = year;
    }
}
