package com.learn.abdevs29.productservicejava.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Category extends BaseModal {
    private String name;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST})
    private List<Product> products;
}
