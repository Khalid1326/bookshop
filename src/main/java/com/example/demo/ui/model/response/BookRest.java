package com.example.demo.ui.model.response;

import com.example.demo.io.entity.UserEntity;

import java.util.List;

/**
 * Created by asus-pc on 4/22/2021.
 */
public class BookRest {
    private String bookId;
    private String tittle;
    private List<UserEntity> users;

    public BookRest() {}

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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}