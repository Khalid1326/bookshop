package com.example.demo.ui.model.response;


import java.util.List;

public class CategoryRest {
    private String categoryId;
    private String name;
    private List<BookRest> books;

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

    public List<BookRest> getBooks() {
        return books;
    }

    public void setBooks(List<BookRest> books) {
        this.books = books;
    }
}