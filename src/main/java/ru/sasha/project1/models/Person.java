package ru.sasha.project1.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Person {


    private int id;

    @NotEmpty(message = "The name should not be empty")
    private String name;

    @Min(value = 1901, message = "born should be minimum 1901")
    @Max(value = 2023, message = "born should be maximum 2023")
    @NotEmpty(message = "Born should not be empty")
    private int born;

    public Person(int id, String name, int born) {
        this.id = id;
        this.name = name;
        this.born = born;
    }

    public Person(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }
}
