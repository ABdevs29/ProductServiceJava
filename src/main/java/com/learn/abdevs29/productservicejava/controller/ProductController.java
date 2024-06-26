package com.learn.abdevs29.productservicejava.controller;

import com.learn.abdevs29.productservicejava.dto.CreateProductRequestDTO;
import com.learn.abdevs29.productservicejava.dto.ProductResponseDTO;
import com.learn.abdevs29.productservicejava.exception.CategoryNotFoundException;
import com.learn.abdevs29.productservicejava.exception.ProductNotFoundException;
import com.learn.abdevs29.productservicejava.model.Category;
import com.learn.abdevs29.productservicejava.model.Product;
import com.learn.abdevs29.productservicejava.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ArrayList<ProductResponseDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ArrayList<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for(Product p : products) {
            productResponseDTOS.add(convertProductToDto(p));
        }
        return productResponseDTOS;
    }

    @GetMapping("/products/{id}")
    public ProductResponseDTO getProductById(@PathVariable("id") Integer id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        if(product == null) {
            throw new ProductNotFoundException("Product not found. Try again!");
        }
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
    public Product createProduct(@RequestBody CreateProductRequestDTO dto) throws CategoryNotFoundException {
        Product p = productService.createProduct(dto.getTitle(), dto.getDescription(), dto.getImage(), dto.getCategory(), dto.getPrice());
        return p;
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody CreateProductRequestDTO dto, @PathVariable("id") Integer id) throws CategoryNotFoundException {
        Product p = productService.updateProduct(id, dto.getTitle(), dto.getDescription(), dto.getImage(), dto.getCategory(), dto.getPrice());
        return p;
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id") Integer id) {
        Product p = productService.deleteProduct(id);
        return p;
    }

    @GetMapping("/products/categories")
    public List<Category> getAllCategories () {
        List<Category> categories = productService.getAllCategories();
        return categories;
    }

    @GetMapping("/products/category/{name}")
    public ArrayList<ProductResponseDTO> getProductsByCategory (@PathVariable("name") String name, @RequestParam(required = false) String limit, @RequestParam(required = false) String sort) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("limit", limit);
        map.put("sort", sort);
        List<Product> products = productService.getProductsByCategory(name, map);
        ArrayList<ProductResponseDTO> result = new ArrayList<>();

        for(Product product: products) {
           result.add(convertProductToDto(product));
        }
        return result;
    }

    @GetMapping("/products/{pageSize}/{pageNo}")
    public ResponseEntity<List<Product>> getPaginatedProducts(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNo") Integer pageNo) {
        List<Product> products = productService.getPaginatedProducts(pageNo, pageSize);
        return ResponseEntity.ok(products);
    }
}
