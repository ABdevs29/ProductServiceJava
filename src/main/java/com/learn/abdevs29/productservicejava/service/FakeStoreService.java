package com.learn.abdevs29.productservicejava.service;

import com.learn.abdevs29.productservicejava.dto.FakeStoreResponseDTO;
import com.learn.abdevs29.productservicejava.dto.ProductResponseDTO;
import com.learn.abdevs29.productservicejava.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreService (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Integer id) {
        ResponseEntity<FakeStoreResponseDTO> response = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreResponseDTO.class);
        FakeStoreResponseDTO fakeStoreResponseDTO = response.getBody();
        return fakeStoreResponseDTO.toProduct();
    }

    @Override
    public Product getAllProducts() {
        return null;
    }
}
