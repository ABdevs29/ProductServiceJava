package com.learn.abdevs29.productservicejava.service;

import com.learn.abdevs29.productservicejava.dto.FakeStoreProductDTO;
import com.learn.abdevs29.productservicejava.model.Category;
import com.learn.abdevs29.productservicejava.model.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreService (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Integer id) {
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
        FakeStoreProductDTO fakeStoreResponseDTO = response.getBody();
        return fakeStoreResponseDTO.toProduct();
    }

    @Override
    public Product[] getAllProducts() {
        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] fakeStoreProductDTOS = response.getBody();
        Product[] arr = new Product[fakeStoreProductDTOS.length];
        for(int i = 0; i < fakeStoreProductDTOS.length; i++) {
            arr[i] = fakeStoreProductDTOS[i].toProduct();
        }
        return arr;
    }

    @Override
    public Product createProduct(String title, String description, String image, String category, Double price) {
        FakeStoreProductDTO requestBody = new FakeStoreProductDTO();
        requestBody.setTitle(title);
        requestBody.setCategory(category);
        requestBody.setDescription(description);
        requestBody.setImage(image);
        requestBody.setPrice(price.toString());
        FakeStoreProductDTO response = restTemplate.postForObject("https://fakestoreapi.com/products", requestBody, FakeStoreProductDTO.class);
        return response.toProduct();
    }

    @Override
    public Category[] getAllCategories() {
        ResponseEntity<String[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products/categories", String[].class);
        String[] categories = response.getBody();

        return convertCategories(categories);
    }

    public Category[] convertCategories(String [] categories) {
        Category[] convertedCategories = new Category[categories.length];

        for (int i = 0; i < categories.length; i++) {
            Category c = new Category();
            c.setName(categories[i]);
            convertedCategories[i] = c;
        }
        return convertedCategories;
    }
}
