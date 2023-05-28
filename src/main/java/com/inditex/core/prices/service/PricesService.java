package com.inditex.core.prices.service;

import com.inditex.core.prices.dto.GetPriceDTO;
import com.inditex.core.prices.dto.GetPriceResponseDTO;
import com.inditex.core.prices.entity.PricesEntity;
import com.inditex.core.prices.mapper.PricesMapper;
import com.inditex.core.prices.repository.PricesRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PricesService {

    @Resource
    private PricesRepository pricesRepository;

    @Resource
    private PricesMapper pricesMapper;

    @Transactional(readOnly = true)
    public GetPriceResponseDTO getPrice(GetPriceDTO getPriceDTO) {
        PricesEntity pricesEntity = pricesRepository.getPrice(getPriceDTO.getDate(), getPriceDTO.getProduct(), getPriceDTO.getBrand());
        return pricesMapper.getPriceResponseDTO(pricesEntity);
    }
}
