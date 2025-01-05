package ru.sasha.project1.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "The title should not be empty")
    private String title;

    @NotEmpty(message = "The author should not be empty")
    @Size(min = 0, max = 50, message = "The author should be maximum 50 characters")
    private String author;

    @Min(value = 1855, message = "The year should be minimum 1855")
    @Max(value = 2024, message = "The year should be maximum 2024")
    private int year;

    private Integer owner_id;


    public Book(int id, String title, String author, int year, Integer owner_id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.owner_id = owner_id;
    }

    public Book(){}

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

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }
}
