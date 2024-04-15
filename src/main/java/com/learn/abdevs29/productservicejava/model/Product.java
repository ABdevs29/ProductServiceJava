package com.learn.abdevs29.productservicejava.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;
    private String title;
    private String description;
    private double price;
    private String image_url;
    private Category category;
}
