package com.learn.abdevs29.productservicejava.model;

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

}
