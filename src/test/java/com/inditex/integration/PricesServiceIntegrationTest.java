package com.inditex.integration;

import com.inditex.core.prices.dto.GetPriceDTO;
import com.inditex.core.prices.dto.GetPriceResponseDTO;
import com.inditex.core.prices.service.PricesService;
import jakarta.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("integration-test")
public class PricesServiceIntegrationTest {

    @Resource
    public PricesService pricesService;

    final private Integer BRAND_ZARA_ID = 1;
    final private Integer PRODUCT_ID = 35455;

    private Double getGetPriceServicePriceResult(String date, Integer brand, Integer product) {
        GetPriceDTO getPriceDTO = new GetPriceDTO(brand, product, date != null ? Timestamp.valueOf(date) : null);
        GetPriceResponseDTO getPriceResponseDTO = pricesService.getPrice(getPriceDTO);
        return getPriceResponseDTO != null ? getPriceResponseDTO.getPrice() : null;
    }

    @Test
    public void test1() {
        final String date = "2020-06-14 10:00:00";
        final Double expectedPrice = 35.5;
        final Double result = getGetPriceServicePriceResult(date, BRAND_ZARA_ID, PRODUCT_ID);
        Assert.assertEquals(expectedPrice, result);
    }

    @Test
    public void test2() {
        final String date = "2020-06-14 16:00:00";
        final Double expectedPrice = 35.5;
        final Double result = getGetPriceServicePriceResult(date, BRAND_ZARA_ID, PRODUCT_ID);
        Assert.assertEquals(expectedPrice, result);
    }

    @Test
    public void test3() {
        final String date = "2020-06-14 21:00:00";
        final Double expectedPrice = 35.5;
        final Double result = getGetPriceServicePriceResult(date, BRAND_ZARA_ID, PRODUCT_ID);
        Assert.assertEquals(expectedPrice, result);
    }

    @Test
    public void test4() {
        final String date = "2020-06-15 10:00:00";
        final Double expectedPrice = 30.5;
        final Double result = getGetPriceServicePriceResult(date, BRAND_ZARA_ID, PRODUCT_ID);
        Assert.assertEquals(expectedPrice, result);
    }

    @Test
    public void test5() {
        final String date = "2020-06-16 21:00:00";
        final Double expectedPrice = 38.95;
        final Double result = getGetPriceServicePriceResult(date, BRAND_ZARA_ID, PRODUCT_ID);
        Assert.assertEquals(expectedPrice, result);
    }

    @Test
    public void outOfRangeBefore() {
        final String date = "1910-06-16 21:00:00";
        final Double result = getGetPriceServicePriceResult(date, BRAND_ZARA_ID, PRODUCT_ID);
        Assert.assertNull(result);
    }

    @Test
    public void outOfRangeAfter() {
        final String date = "2100-06-16 21:00:00";
        final Double result = getGetPriceServicePriceResult(date, BRAND_ZARA_ID, PRODUCT_ID);
        Assert.assertNull(result);
    }

    @Test
    public void nullBrand() {
        final String date = "2010-06-16 21:00:00";
        final Double result = getGetPriceServicePriceResult(date, null, PRODUCT_ID);
        Assert.assertNull(result);
    }


    @Test
    public void nullProduct() {
        final String date = "2010-06-16 21:00:00";
        final Double result = getGetPriceServicePriceResult(date, BRAND_ZARA_ID, null);
        Assert.assertNull(result);
    }

    @Test
    public void nullBrandAndProduct() {
        final String date = "2010-06-16 21:00:00";
        final Double result = getGetPriceServicePriceResult(date, null, null);
        Assert.assertNull(result);
    }

    @Test
    public void nonexistentBrand() {
        final String date = "2010-06-16 21:00:00";
        final Double result = getGetPriceServicePriceResult(date, Integer.MAX_VALUE, PRODUCT_ID);
        Assert.assertNull(result);
    }

    @Test
    public void nonexistentProduct() {
        final String date = "2010-06-16 21:00:00";
        final Double result = getGetPriceServicePriceResult(date, BRAND_ZARA_ID, Integer.MAX_VALUE);
        Assert.assertNull(result);
    }


    @Test
    public void nonExistentBrandAndProduct() {
        final String date = "2010-06-16 21:00:00";
        final Double result = getGetPriceServicePriceResult(date, Integer.MAX_VALUE, Integer.MAX_VALUE);
        Assert.assertNull(result);
    }

    @Test
    public void nullDate() {
        final Double result = getGetPriceServicePriceResult(null, BRAND_ZARA_ID, PRODUCT_ID);
        Assert.assertNull(result);
    }

}
