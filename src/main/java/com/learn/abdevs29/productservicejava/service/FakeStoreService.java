package com.learn.abdevs29.productservicejava.service;

import com.learn.abdevs29.productservicejava.dto.FakeStoreProductDTO;
import com.learn.abdevs29.productservicejava.model.Category;
import com.learn.abdevs29.productservicejava.model.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("fakeStoreService")
public class FakeStoreService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreService (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Integer id) {
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
        System.out.println(response.getStatusCode());
        if(response.getBody() == null) {
            return null;
        } else {
            FakeStoreProductDTO fakeStoreResponseDTO = response.getBody();
            return fakeStoreResponseDTO.toProduct();
        }
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] fakeStoreProductDTOS = response.getBody();
        ArrayList<Product> arr = new ArrayList<>();
        for(FakeStoreProductDTO fp : fakeStoreProductDTOS) {
            arr.add(fp.toProduct());
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
    public Product updateProduct(Integer id, String title, String description, String image, String category, Double price) {
        FakeStoreProductDTO requestBody = new FakeStoreProductDTO();
        requestBody.setId(id);
        requestBody.setTitle(title);
        requestBody.setCategory(category);
        requestBody.setDescription(description);
        requestBody.setImage(image);
        requestBody.setPrice(price.toString());

        ResponseEntity<FakeStoreProductDTO> response = restTemplate.exchange("https://fakestoreapi.com/products/" + id, HttpMethod.PUT ,new HttpEntity<>(requestBody), FakeStoreProductDTO.class);
        return response.getBody().toProduct();
    }

    @Override
    public Product deleteProduct(Integer id) {
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.exchange("https://fakestoreapi.com/products/" + id, HttpMethod.DELETE, null , FakeStoreProductDTO.class);
        return response.getBody().toProduct();
    }

    @Override
    public ArrayList<Category> getAllCategories() {
        ResponseEntity<String[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products/categories", String[].class);
        String[] categories = response.getBody();

        return convertCategories(categories);
    }

    public ArrayList<Category> convertCategories(String [] categories) {
        ArrayList<Category> convertedCategories = new ArrayList<>();

        for (String category : categories) {
            Category c = new Category();
            c.setName(category);
            convertedCategories.add(c);
        }
        return convertedCategories;
    }

    @Override
    public ArrayList<Product> getProductsByCategory(String name, Map<String, String> map) {
        FakeStoreProductDTO[] fakeStoreProductDTOS = restTemplate.getForObject("https://fakestoreapi.com/products/category/{name}?limit={limit}&sort={sort}", FakeStoreProductDTO[].class, map);
        ArrayList<Product> products = new ArrayList<>();

        for (FakeStoreProductDTO fs : fakeStoreProductDTOS) {
            products.add(fs.toProduct());
        }
        return products;
    }

    @Override
    public List<Product> getPaginatedProducts(Integer pageNo, Integer pageSize) {
        return null;
    }
}
