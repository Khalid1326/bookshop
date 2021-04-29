package com.example.demo.ui.controller;


import com.example.demo.exeptions.ServiceException;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.UserService;
import com.example.demo.ui.model.request.BookDetailsRequestModel;
import com.example.demo.ui.model.response.BookRest;
import com.example.demo.ui.model.response.ErrorMessages;
import com.example.demo.ui.model.response.OperationStatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books") // http://localhost:8080/books
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @GetMapping(path = "/{id}")
    public BookRest getBook (@PathVariable String id){
        BookRest returnValue = bookService.getBookByBookId(id);
        return returnValue;
    }

    @PostMapping()
    public BookRest createBook(@RequestBody BookDetailsRequestModel bookDetails){

        if (bookDetails.getTittle().isEmpty()) {
            throw new ServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage().toString());
        }

        BookRest createdBook = bookService.creatBook(bookDetails);
        return createdBook;
    }
    @PutMapping(path = "/{id}")
    public BookRest updateBook(@PathVariable String id, @RequestBody BookDetailsRequestModel bookDetails){
        BookRest updatedBook = bookService.updateBook(id, bookDetails);
        return updatedBook;
    }
    @GetMapping()
    public List<BookRest> getBooks(@RequestParam(value ="page", defaultValue = "0") int page,
                                            @RequestParam(value = "limit", defaultValue = "25") int limit){
        List<BookRest> books = bookService.getBooks(page, limit);
        return books;
    }
    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteBook(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOpertaionName("DELETE");
        bookService.deleteBook(id);
        returnValue.setOpertaionResult("SUCCESS");
        return returnValue;
    }
}