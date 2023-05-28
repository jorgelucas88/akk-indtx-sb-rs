package com.inditex.core.prices.entity;

import com.inditex.core.Constants.Currency;
import com.inditex.core.brands.entity.BrandsEntity;
import com.inditex.core.products.entity.ProductsEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "PRICES")
@Data
public class PricesEntity {
    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "brand_id")
    private BrandsEntity brand;

    private Timestamp startDate;
    private Timestamp endDate;
    private Integer priceList;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductsEntity product;

    private Integer priority;
    private Double price;

    @Enumerated(EnumType.STRING)
    private Currency curr;
}
