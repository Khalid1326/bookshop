package com.example.demo.ui.model.request;

/**
 * Created by asus-pc on 4/22/2021.
 */
public class BookDetailsRequestModel {
    private String tittle;
    private String categoryId;
    private String userId;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}