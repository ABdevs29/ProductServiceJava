package com.learn.abdevs29.productservicejava.service;

import com.learn.abdevs29.productservicejava.exception.CategoryNotFoundException;
import com.learn.abdevs29.productservicejava.model.Category;
import com.learn.abdevs29.productservicejava.model.Product;
import com.learn.abdevs29.productservicejava.repository.CategoryRepository;
import com.learn.abdevs29.productservicejava.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        // get products from db
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String description, String image, String category, Double price) throws CategoryNotFoundException {
        Product productToBeCreated = new Product();
        Category fetchedCategory = categoryRepository.findByName(category);
        if (fetchedCategory == null) {
            throw new CategoryNotFoundException("Category not found");
        }

        productToBeCreated.setTitle(title);
        productToBeCreated.setDescription(description);
        productToBeCreated.setImage_url(image);
        productToBeCreated.setCategory(fetchedCategory);
        productToBeCreated.setPrice(price.toString());

        Product productAdded = productRepository.save(productToBeCreated);
        return productAdded;
    }

    @Override
    public Product updateProduct(Integer id, String title, String description, String image, String category, Double price) throws CategoryNotFoundException {
        Product productToBeUpdated = productRepository.findProductById(id);
        Category fetchedCategory = categoryRepository.findByName(category);
        if (fetchedCategory == null) {
            throw new CategoryNotFoundException("Category not found");
        }

        productToBeUpdated.setTitle(title);
        productToBeUpdated.setDescription(description);
        productToBeUpdated.setImage_url(image);
        productToBeUpdated.setCategory(fetchedCategory);
        productToBeUpdated.setPrice(price.toString());

        return productRepository.save(productToBeUpdated);
    }

    @Override
    public Product deleteProduct(Integer id) {
        Product productToBeDeleted = productRepository.findProductById(id);
        productRepository.deleteById(id);
        return productToBeDeleted;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String name, Map<String, String> map) {
        Category fetchedCategory = categoryRepository.findByName(name);

        if (map.containsKey("sort")) {
            if (map.get("sort").equals("desc")) {
                return productRepository.findProductByCategoryOrderByTitleDesc(fetchedCategory);
            } else {
                return productRepository.findProductByCategoryOrderByTitleAsc(fetchedCategory);
            }
        }

        return productRepository.findProductByCategory(fetchedCategory);
    }

    @Override
    public List<Product> getPaginatedProducts(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> products = productRepository.findAll(pageable);
        return products.toList();
    }

}
