package com.inditex.core.prices.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class GetPriceDTO {
    private Integer brand;
    private Integer product;
    private Timestamp date;

    public GetPriceDTO(Integer brand, Integer product, Timestamp date) {
        this.date = date;
        this.brand = brand;
        this.product = product;
    }
}
