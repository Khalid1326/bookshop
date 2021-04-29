package com.example.demo.io.repository;

import com.example.demo.io.entity.CategoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by asus-pc on 4/22/2021.
 */

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity,Long> {
    CategoryEntity findByName(String name);
    CategoryEntity findByCategoryId (String categoryId);
}