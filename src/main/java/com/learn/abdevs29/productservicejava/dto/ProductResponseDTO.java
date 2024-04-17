package com.learn.abdevs29.productservicejava.dto;

import com.learn.abdevs29.productservicejava.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private long id;
    private String title;
    private String description;
    private String price;
    private String image_url;
    private Category category;
}
