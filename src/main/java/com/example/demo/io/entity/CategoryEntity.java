package com.example.demo.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity(name = "categories")
public class CategoryEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String categoryId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "categoryDetails", cascade = CascadeType.ALL)
    private List<BookEntity> books;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}