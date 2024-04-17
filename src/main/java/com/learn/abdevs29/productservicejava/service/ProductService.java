package com.learn.abdevs29.productservicejava.service;

import com.learn.abdevs29.productservicejava.model.Product;

public interface ProductService {
    Product getProductById (Integer id);
    Product getAllProducts();
}
