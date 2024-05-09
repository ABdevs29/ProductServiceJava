package com.learn.abdevs29.productservicejava.repository;

import com.learn.abdevs29.productservicejava.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByName(String name);

    List<Category> findAll();
}
