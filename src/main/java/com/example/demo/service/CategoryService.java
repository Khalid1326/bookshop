package com.example.demo.service;

import com.example.demo.io.entity.CategoryEntity;
import com.example.demo.shared.dto.CategoryDto;
import com.example.demo.ui.model.request.BookDetailsRequestModel;
import com.example.demo.ui.model.request.CategoryDetailsRequestModel;
import com.example.demo.ui.model.response.CategoryRest;

import java.util.List;

/**
 * Created by asus-pc on 4/22/2021.
 */
public interface CategoryService {
    CategoryRest createCategory(CategoryDetailsRequestModel categoryDetailsRequestModel);
    CategoryRest getCategoryByCategoryId (String userId);
    CategoryRest updateCategory (String categoryId, CategoryDetailsRequestModel categoryDetailsRequestModel);
    List<CategoryRest> getCategories (int page, int limit);
    void deleteCategory (String categoryId);
}