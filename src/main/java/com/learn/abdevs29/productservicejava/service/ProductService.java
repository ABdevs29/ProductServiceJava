package com.learn.abdevs29.productservicejava.service;

import com.learn.abdevs29.productservicejava.model.Category;
import com.learn.abdevs29.productservicejava.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Product getProductById (Integer id);
    ArrayList<Product> getAllProducts();
    Product createProduct(String title, String description, String image, String category, Double price);
    ArrayList<Category> getAllCategories();
    ArrayList<Product> getProductsByCategory(String name);
}
