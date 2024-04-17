package com.learn.abdevs29.productservicejava.controller;

import com.learn.abdevs29.productservicejava.dto.CreateProductRequestDTO;
import com.learn.abdevs29.productservicejava.dto.ProductResponseDTO;
import com.learn.abdevs29.productservicejava.model.Category;
import com.learn.abdevs29.productservicejava.model.Product;
import com.learn.abdevs29.productservicejava.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ProductResponseDTO[] getAllProducts() {
        Product[] products = productService.getAllProducts();
        ProductResponseDTO[] productResponseDTOS = new ProductResponseDTO[products.length];
        for(int i = 0; i < products.length; i++) {
            productResponseDTOS[i] = convertProductToDto(products[i]);
        }
        return productResponseDTOS;
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
    public Product createProduct(@RequestBody CreateProductRequestDTO dto) {
        Product p = productService.createProduct(dto.getTitle(), dto.getDescription(), dto.getImage(), dto.getCategory(), dto.getPrice());
        return p;
    }

    @GetMapping("/products/categories")
    public Category[] getAllCategories () {
        Category[] categories = productService.getAllCategories();
        return categories;
    }

    @GetMapping("/products/category/{name}")
    public ProductResponseDTO[] getProductsByCategory (@PathVariable("name") String name) {
        Product[] products = productService.getProductsByCategory(name);
        ProductResponseDTO[] result = new ProductResponseDTO[products.length];

        for(int i = 0; i < products.length; i++) {
            result[i] = convertProductToDto(products[i]);
        }
        return result;
    }
}
