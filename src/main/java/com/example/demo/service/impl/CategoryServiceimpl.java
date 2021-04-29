package com.example.demo.service.impl;

import com.example.demo.io.entity.BookEntity;
import com.example.demo.io.entity.CategoryEntity;
import com.example.demo.io.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.BookDto;
import com.example.demo.shared.dto.CategoryDto;
import com.example.demo.ui.model.request.BookDetailsRequestModel;
import com.example.demo.ui.model.request.CategoryDetailsRequestModel;
import com.example.demo.ui.model.response.CategoryRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceimpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    Utils utils;


//    @Override
//    public CategoryRest creatCategory(CategoryDto category) {
//        CategoryEntity categoryEntitye = categoryRepository.findByName(category.getName());
//        if (categoryEntitye != null) throw new RuntimeException("category already exists");
//        for (int i=0; i < category.getBooks().size(); i++){
//            BookDto book = category.getBooks().get(i);
//            book.setCategoryDetails(category);
//            book.setBookId(utils.generateBookId(30));
//            category.getBooks().set(i,book);
//        }
//        ModelMapper modelMapper = new ModelMapper();
//        CategoryEntity categoryEntity = modelMapper.map(category, CategoryEntity.class);
//        String publicCategoryId = utils.generateCategoryId(30);
//        categoryEntity.setCategoryId(publicCategoryId);
//        CategoryEntity storedCategoryDetails = categoryRepository.save(categoryEntity);
//        CategoryRest returnValue = modelMapper.map(storedCategoryDetails, CategoryRest.class);
//        return returnValue;
////        CategoryEntity categoryEntity = new CategoryEntity();
////        ModelMapper modelMapper1 = new ModelMapper();
////        List<BookEntity> bookEntityList = new ArrayList<>();
////        for (int i=0; i < category.getBooks().size(); i++){
////            BookDetailsRequestModel book = category.getBooks().get(i);
////            BookEntity bookEntity = modelMapper1.map(book,BookEntity.class);
////            String publicBookId = utils.generateBookId(30);
////            bookEntity.setTittle(book.getTittle());
////            bookEntity.setBookId(publicBookId);
////            //bookEntity.setCategoryDetails(modelMapper1.map(category, CategoryEntity.class));
////            bookEntityList.add(bookEntity);
////        }
////        String publicCategoryId = utils.generateCategoryId(30);
////        categoryEntity.setCategoryId(publicCategoryId);
////        categoryEntity.setName(category.getName());
////        categoryEntity.setBooks(bookEntityList);
////        CategoryEntity storedCategoryDetails = categoryRepository.save(categoryEntity);
////        ModelMapper modelMapper = new ModelMapper();
////        CategoryRest returnValue = modelMapper.map(storedCategoryDetails, CategoryRest.class);
////        return returnValue;
//    }

    @Override
    public CategoryRest createCategory(CategoryDetailsRequestModel category){

        CategoryEntity categoryEntitye = categoryRepository.findByName(category.getName());
        if (categoryEntitye != null) throw new RuntimeException("category already exists");

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName(category.getName());

        List<BookEntity> bookEntityList = new ArrayList<>();

        for (BookDetailsRequestModel book : category.getBooks()){
            BookEntity bookEntity = new BookEntity();
            bookEntity.setTittle(book.getTittle());
            bookEntity.setBookId(utils.generateBookId(30));
            bookEntity.setCategoryDetails(categoryEntity);
            bookEntityList.add(bookEntity);
        }

        categoryEntity.setCategoryId(utils.generateCategoryId(30));
        categoryEntity.setBooks(bookEntityList);

        CategoryEntity storedCategoryDetails = categoryRepository.save(categoryEntity);

        ModelMapper modelMapper = new ModelMapper();

        CategoryRest returnValue = modelMapper.map(storedCategoryDetails, CategoryRest.class);

        return returnValue;
    }
    @Override
    public CategoryRest getCategoryByCategoryId(String categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
        CategoryRest returnValue = new CategoryRest();
        BeanUtils.copyProperties(categoryEntity, returnValue);
        return returnValue;
    }

    @Override
    public CategoryRest updateCategory(String categoryId, CategoryDetailsRequestModel category) {

        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);

        categoryEntity.setName(category.getName());

        CategoryEntity updatedCategoryDetails = categoryRepository.save(categoryEntity);

        ModelMapper modelMapper = new ModelMapper();

        CategoryRest returnValue = modelMapper.map(updatedCategoryDetails, CategoryRest.class);

        return returnValue;
    }
    @Override
    public List<CategoryRest> getCategories(int page, int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<CategoryEntity> categoriesPage = categoryRepository.findAll(pageableRequest);
        List<CategoryEntity> categories = categoriesPage.getContent();
        List<CategoryRest> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (CategoryEntity categoryEntity : categories){
            CategoryRest categoryRest = modelMapper.map(categoryEntity, CategoryRest.class);
            returnValue.add(categoryRest);
        }
        return returnValue;
    }

    @Override
    public void deleteCategory(String categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);

//        if (categoryEntity == null)
//            throw new CategoryNotFoundException(categoryId);
        categoryRepository.delete(categoryEntity);
    }

//    @Override
//    public CategoryRest addBook(String categoryId, CategoryDetailsRequestModel category) {
//
//        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
//
//        List<BookEntity> bookEntityList = new ArrayList<>();
//
//        for (BookEntity book : categoryEntity.getBooks()){
//            BookEntity bookEntity = new BookEntity();
//            bookEntity.setTittle(book.getTittle());
//            if (book.getBookId()!= null){
//                bookEntity.setBookId(book.getBookId());
//            }
//            else {
//                bookEntity.setBookId(utils.generateBookId(30));
//            }
//            bookEntity.setCategoryDetails(categoryEntity);
//            bookEntityList.add(bookEntity);
//        }
//
//        categoryEntity.setBooks(bookEntityList);
//
//        CategoryEntity storedCategoryDetails = categoryRepository.save(categoryEntity);
//
//        ModelMapper modelMapper = new ModelMapper();
//
//        CategoryRest returnValue = modelMapper.map(storedCategoryDetails, CategoryRest.class);
//
//        return returnValue;
//    }
}