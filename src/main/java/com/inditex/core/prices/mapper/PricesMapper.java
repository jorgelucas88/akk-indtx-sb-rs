package com.inditex.core.prices.mapper;

import com.inditex.core.prices.dto.GetPriceResponseDTO;
import com.inditex.core.prices.entity.PricesEntity;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class PricesMapper {
    public GetPriceResponseDTO getPriceResponseDTO(PricesEntity pricesEntity) {
        if (pricesEntity == null) {
            return null;
        }
        GetPriceResponseDTO getPriceResponseDTO = new GetPriceResponseDTO();
        getPriceResponseDTO.setBrand(pricesEntity.getBrand().getName());
        getPriceResponseDTO.setProduct(pricesEntity.getProduct().getName());
        getPriceResponseDTO.setPrice(pricesEntity.getPrice());
        getPriceResponseDTO.setStartDate(pricesEntity.getStartDate());
        getPriceResponseDTO.setEndDate(pricesEntity.getEndDate());
        return getPriceResponseDTO;
    }
}
