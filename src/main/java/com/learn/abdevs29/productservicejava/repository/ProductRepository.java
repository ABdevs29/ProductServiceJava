package com.learn.abdevs29.productservicejava.repository;

import com.learn.abdevs29.productservicejava.model.Category;
import com.learn.abdevs29.productservicejava.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product save(Product p);

    List<Product> findAll();

    Product findProductById(Integer id);

    Product findByDescription(String description);

    void deleteById(Integer id);

    List<Product> findProductByCategoryOrderByTitleAsc(Category c);
    List<Product> findProductByCategoryOrderByTitleDesc(Category c);
    List<Product> findProductByCategory(Category c);
}
