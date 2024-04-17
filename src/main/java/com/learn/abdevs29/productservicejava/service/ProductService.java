package com.learn.abdevs29.productservicejava.service;

import com.learn.abdevs29.productservicejava.model.Category;
import com.learn.abdevs29.productservicejava.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById (Integer id);
    Product[] getAllProducts();
    Product createProduct(String title, String description, String image, String category, Double price);
    Category[] getAllCategories();
}
