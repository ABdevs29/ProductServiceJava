package com.learn.abdevs29.productservicejava.controller;

import com.learn.abdevs29.productservicejava.dto.ProductResponseDTO;
import com.learn.abdevs29.productservicejava.model.Product;
import com.learn.abdevs29.productservicejava.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getAllProducts() {
        return "Get all products";
    }

    @GetMapping("/products/{id}")
    public ProductResponseDTO getProductById(@PathVariable("id") Integer id) {
        Product product = productService.getProductById(id);
        //conversion from product to DTO
        return convertProductToDto(product);
    }

    private ProductResponseDTO convertProductToDto(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setImage_url(product.getImage_url());
        dto.setCategory(product.getCategory());

        return dto;
    }

    @PostMapping("/products")
    public String createProduct() {
        return "Create a product";
    }
}
