package com.inditex.core.prices.endpoint;

import com.inditex.core.prices.dto.GetPriceDTO;
import com.inditex.core.prices.dto.GetPriceResponseDTO;
import com.inditex.core.prices.service.PricesService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;

@Controller
@RequestMapping("/prices")
public class PricesEndpoint {

    @Resource
    private PricesService pricesService;

    @GetMapping(value = "/getPrice/{brand}/{product}/{date}", produces = "application/json")
    public ResponseEntity<GetPriceResponseDTO> getPrice(@PathVariable Integer brand,
                                                        @PathVariable Integer product,
                                                        @PathVariable Timestamp date
                                                        ) {
        GetPriceDTO getPriceDTO = new GetPriceDTO(brand, product, date);
        return ResponseEntity.ok(pricesService.getPrice(getPriceDTO));
    }
}
