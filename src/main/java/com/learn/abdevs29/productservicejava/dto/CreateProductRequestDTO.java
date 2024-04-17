package com.learn.abdevs29.productservicejava.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductRequestDTO {
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
}
