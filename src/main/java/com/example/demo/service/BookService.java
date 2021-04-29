package com.example.demo.service;

import com.example.demo.shared.dto.BookDto;
import com.example.demo.ui.model.request.BookDetailsRequestModel;
import com.example.demo.ui.model.response.BookRest;

import java.util.List;

/**
 * Created by asus-pc on 4/14/2021.
 */
public interface BookService {
    BookRest creatBook (BookDetailsRequestModel bookDetailsRequestModel);
    BookRest getBookByBookId (String bookId);
    BookRest updateBook (String bookId, BookDetailsRequestModel bookDetailsRequestModel);
    List<BookRest> getBooks (int page, int limit);
    List<BookDto> getCategoryBooks(String categoryId);
    BookDto getBook(String bookId);
    void deleteBook(String bookId);
}