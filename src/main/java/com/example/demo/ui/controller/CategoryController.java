package com.example.demo.ui.controller;

import com.example.demo.exeptions.ServiceException;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import com.example.demo.shared.dto.BookDto;
import com.example.demo.ui.model.request.CategoryDetailsRequestModel;
import com.example.demo.ui.model.response.BookRest;
import com.example.demo.ui.model.response.CategoryRest;
import com.example.demo.ui.model.response.ErrorMessages;
import com.example.demo.ui.model.response.OperationStatusModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "categories" , produces = "Application/json") // http://localhost:8080/categories
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    BookService bookService;

    @Autowired
    BookService booksService;

    @GetMapping(path = "/{id}")
    public CategoryRest getCategory(@PathVariable String id) {
        CategoryRest returnValue = categoryService.getCategoryByCategoryId(id);
        return returnValue;
    }

    @PostMapping()
    public CategoryRest createCategory(@RequestBody CategoryDetailsRequestModel categoryDetails) {
//        ModelMapper modelMapper = new ModelMapper();
//        CategoryDto categoryDto = modelMapper.map(categoryDetails, CategoryDto.class);
//        CategoryRest createdCategory = categoryService.creatCategory(categoryDto);

        if (categoryDetails.getName().isEmpty()) {
            throw new ServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage().toString());
        }

        CategoryRest createdCategory = categoryService.createCategory(categoryDetails);
        return createdCategory;
    }

    @PutMapping(path = "/{id}")
    public CategoryRest updateCategory(@PathVariable String id, @RequestBody CategoryDetailsRequestModel categoryDetails) {
//        ModelMapper modelMapper = new ModelMapper();
//        CategoryDto categoryDto = modelMapper.map(categoryDetails, CategoryDto.class);
        CategoryRest updatedCategory = categoryService.updateCategory(id, categoryDetails);
        return updatedCategory;
//
//        UserRest returnValue = new UserRest();
//
//        UserDto userDto = new UserDto();
//        BeanUtils.copyProperties(userDetails, userDto);
//
//        UserDto updatedUser = userService.updateUser(id, userDto);
//        BeanUtils.copyProperties(updatedUser, returnValue);
//
//        return returnValue;
//
//        //CategoryRest updatedCategory = new CategoryRest();
//        CategoryDto categoryDto1 = new CategoryDto();
//        BeanUtils.copyProperties(categoryDetails, categoryDto1);
//        CategoryRest updatedCategory = categoryService.updateCategory(id, categoryDto1);
//        return updatedCategory;
//        CategoryDto categoryDto = new CategoryDto();
//        BeanUtils.copyProperties(categoryDetails, categoryDto);
//        CategoryRest updatedCategory = categoryService.updateCategory(id, categoryDto);
//        return updatedCategory;

            }

    @GetMapping()
    public List<CategoryRest> getCategories(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<CategoryRest> categories = categoryService.getCategories(page, limit);
        return categories;
    }

    @GetMapping(path = "/{id}/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookRest> getCategoryBooks(@PathVariable String id) {
        List<BookRest> returnValue = new ArrayList<>();
        List<BookDto> bookDTO = booksService.getCategoryBooks(id);

        if (bookDTO != null && !bookDTO.isEmpty()) {
            Type listType = new TypeToken<List<BookRest>>() {}.getType();
            returnValue = new ModelMapper().map(bookDTO, listType);
        }
        return returnValue;
    }

    @GetMapping(path = "/{categoryId}/books/{bookId}")
    public BookRest getUserAddress(@PathVariable String bookId) {
        BookDto bookDto = bookService.getBook(bookId);
        return new ModelMapper().map(bookDto, BookRest.class);
    }

//    @PostMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public CategoryRest addBookToCategory(@PathVariable String id, @RequestBody CategoryDetailsRequestModel categoryDetails){
//
//        CategoryRest updatedCategory = categoryService.addBook(id, categoryDetails);
//        return updatedCategory;
//    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteCategory(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOpertaionName("DELETE");
        categoryService.deleteCategory(id);
        returnValue.setOpertaionResult("SUCCESS");
        return returnValue;
    }
}