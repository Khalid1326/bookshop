package com.example.demo.io.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity(name = "books")
public class BookEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String bookId;

    @Column
    private String tittle ;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryDetails;

    @JsonIgnore
    @ManyToMany(mappedBy ="books")
    private List<UserEntity> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public CategoryEntity getCategoryDetails() {
        return categoryDetails;
    }

    public void setCategoryDetails(CategoryEntity categoryDetails) {
        this.categoryDetails = categoryDetails;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}