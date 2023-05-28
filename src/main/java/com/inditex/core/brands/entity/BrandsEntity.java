package com.inditex.core.brands.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "BRANDS")
@Data
public class BrandsEntity {
    @Id
    private Long id;
    private String name;
}
