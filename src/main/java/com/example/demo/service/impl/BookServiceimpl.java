package com.example.demo.service.impl;

import com.example.demo.io.entity.BookEntity;
import com.example.demo.io.entity.CategoryEntity;
import com.example.demo.io.entity.UserEntity;
import com.example.demo.io.repository.BookRepository;
import com.example.demo.io.repository.CategoryRepository;
import com.example.demo.io.repository.UserRepository;
import com.example.demo.service.BookService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.BookDto;
import com.example.demo.ui.model.request.BookDetailsRequestModel;
import com.example.demo.ui.model.response.BookRest;
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
public class BookServiceimpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public BookRest creatBook(BookDetailsRequestModel book) {

        BookEntity bookEntitye = bookRepository.findByTittle(book.getTittle());
        if (bookEntitye != null) throw new RuntimeException("book already exists");

        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(book.getCategoryId());
        UserEntity userEntity = userRepository.findByUserId(book.getUserId());
        List<UserEntity> userEntityList = new ArrayList<>();

        BookEntity bookEntity = new BookEntity();

        String publicBookId = utils.generateBookId(30);

        bookEntity.setBookId(publicBookId);
        bookEntity.setTittle(book.getTittle());
        bookEntity.setCategoryDetails(categoryEntity);

        userEntity.getBooks().add(bookEntity);
        userEntityList.add(userEntity);

        bookEntity.setUsers(userEntityList);

        BookEntity storedBookDetails = bookRepository.save(bookEntity);
        ModelMapper modelMapper = new ModelMapper();
        BookRest returnValue = modelMapper.map(storedBookDetails, BookRest.class);
        return returnValue;
    }

    @Override
    public BookRest getBookByBookId(String bookId) {
        BookEntity bookEntity = bookRepository.findByBookId(bookId);
        BookRest returnValue = new BookRest();
        BeanUtils.copyProperties(bookEntity, returnValue);
        return returnValue;
    }

    @Override
    public BookRest updateBook(String bookId, BookDetailsRequestModel bookDetailsRequestModel) {
        BookEntity bookEntity = bookRepository.findByBookId(bookId);
        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(bookDetailsRequestModel.getCategoryId());
        bookEntity.setTittle(bookDetailsRequestModel.getTittle());
        bookEntity.setCategoryDetails(categoryEntity);
        BookEntity updatedBookDetails = bookRepository.save(bookEntity);
        ModelMapper modelMapper= new ModelMapper();
        BookRest returnValue = modelMapper.map(updatedBookDetails, BookRest.class);
        return returnValue;
    }

    @Override
    public List<BookRest> getBooks(int page, int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<BookEntity> booksPage = bookRepository.findAll(pageableRequest);
        List<BookEntity> books = booksPage.getContent();
        List<BookRest> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (BookEntity bookEntity : books){
            BookRest bookRest = modelMapper.map(bookEntity, BookRest.class);
            returnValue.add(bookRest);
        }
        return returnValue;
    }

    @Override
    public List<BookDto> getCategoryBooks(String categoryId) {

        List<BookDto> returnValue = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();

        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);

        if (categoryEntity==null) return returnValue;

        List<BookEntity> books = bookRepository.findAllByCategoryDetails(categoryEntity);

        for (BookEntity bookEntity : books){
            returnValue.add(modelMapper.map(bookEntity, BookDto.class));
        }

        return returnValue;
    }
    @Override
    public BookDto getBook(String bookId) {
        BookDto returnValue = null;
        BookEntity bookEntity = bookRepository.findByBookId(bookId);
        if (bookEntity != null){
            return new ModelMapper().map(bookEntity,BookDto.class);
        }
        return returnValue;
    }

    @Override
    public void deleteBook(String bookId) {
        BookEntity bookEntity = bookRepository.findByBookId(bookId);

//        if (bookEntity == null)
//            throw new BookNotFoundException(bookId);
        bookRepository.delete(bookEntity);
    }
}