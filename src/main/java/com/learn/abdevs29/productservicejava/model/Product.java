package com.learn.abdevs29.productservicejava.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Product extends BaseModal {
    private String title;
    private String description;
    private String price;
    private String image_url;

    @ManyToOne
    private Category category;
}
