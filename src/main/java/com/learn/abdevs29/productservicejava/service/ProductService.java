package com.learn.abdevs29.productservicejava.service;

import com.learn.abdevs29.productservicejava.exception.CategoryNotFoundException;
import com.learn.abdevs29.productservicejava.model.Category;
import com.learn.abdevs29.productservicejava.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Product getProductById (Integer id);
    List<Product> getAllProducts();
    Product createProduct(String title, String description, String image, String category, Double price) throws CategoryNotFoundException;
    Product updateProduct(Integer id, String title, String description, String image, String category, Double price) throws CategoryNotFoundException;
    Product deleteProduct(Integer id);
    List<Category> getAllCategories();
    List<Product> getProductsByCategory(String name, Map<String, String> map);
    List<Product> getPaginatedProducts(Integer pageNo, Integer pageSize);
}
