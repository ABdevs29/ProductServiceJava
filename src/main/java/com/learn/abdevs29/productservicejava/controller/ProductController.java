package com.learn.abdevs29.productservicejava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/products")
    public String getAllProducts() {
        return "Get all products";
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable("id") Long id) {
        return "Get product by id: " + id;
    }

    @PostMapping("/products")
    public String createProduct() {
        return "Create a product";
    }
}
