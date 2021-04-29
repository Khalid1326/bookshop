package com.example.demo.ui.model.request;


import java.util.List;

public class CategoryDetailsRequestModel {
    private String name;
    private List<BookDetailsRequestModel> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookDetailsRequestModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookDetailsRequestModel> books) {
        this.books = books;
    }
}
