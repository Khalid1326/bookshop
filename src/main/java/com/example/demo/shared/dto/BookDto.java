package com.example.demo.shared.dto;


import java.io.Serializable;

public class BookDto implements Serializable {
    private long id;
    private String bookId;
    private String tittle;
    private CategoryDto categoryDetails;

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

    public CategoryDto getCategoryDetails() {
        return categoryDetails;
    }

    public void setCategoryDetails(CategoryDto categoryDetails) {
        this.categoryDetails = categoryDetails;
    }

}