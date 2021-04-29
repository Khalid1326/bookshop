package com.example.demo.io.repository;

import com.example.demo.io.entity.BookEntity;
import com.example.demo.io.entity.CategoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<BookEntity,Long> {
    BookEntity findByTittle(String tittle);
    BookEntity findByBookId(String bookId);
    List<BookEntity> findAllByCategoryDetails(CategoryEntity categoryEntity);
}