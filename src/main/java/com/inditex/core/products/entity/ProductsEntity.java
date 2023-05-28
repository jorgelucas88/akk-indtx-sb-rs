package com.inditex.core.products.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PRODUCTS")
@Data
public class ProductsEntity {
    @Id
    private Long id;
    private String name;
}
