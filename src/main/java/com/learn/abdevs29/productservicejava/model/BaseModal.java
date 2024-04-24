package com.learn.abdevs29.productservicejava.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseModal {
    @Id
    private Integer id;
    private Date createdAt;
    private Date updatedAt;
}
