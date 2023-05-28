package com.inditex.core.prices.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class GetPriceResponseDTO {
    private String brand;
    private String product;
    private Double price;
    private Timestamp startDate;
    private Timestamp endDate;
}
