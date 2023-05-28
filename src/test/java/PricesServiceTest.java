import com.inditex.InditexSbApplication;
import com.inditex.core.brands.entity.BrandsEntity;
import com.inditex.core.prices.dto.GetPriceDTO;
import com.inditex.core.prices.dto.GetPriceResponseDTO;
import com.inditex.core.prices.entity.PricesEntity;
import com.inditex.core.prices.mapper.PricesMapper;
import com.inditex.core.prices.repository.PricesRepository;
import com.inditex.core.prices.service.PricesService;
import com.inditex.core.products.entity.ProductsEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = InditexSbApplication.class)
@SpringBootTest
public class PricesServiceTest {

    @Mock
    private PricesRepository pricesRepository;
    @Mock
    private PricesMapper pricesMapper;

    @InjectMocks
    private PricesService pricesService;


    final private Integer RANDOM_STRING_LENGTH = 10;

    @Test
    public void testGetPrice() {
        Integer brandId = (int) Math.random();
        Integer productId = (int) Math.random();
        String product = RandomStringUtils.random(RANDOM_STRING_LENGTH);
        String brand = RandomStringUtils.random(RANDOM_STRING_LENGTH);
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        Double price = Math.random();


        Mockito.when(pricesRepository.getPrice(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(getPricesEntity(price, brand, product));
        Mockito.when(pricesMapper.getPriceResponseDTO(Mockito.any())).thenReturn(getGetPriceResponseDTO(product, brand, price));

        GetPriceDTO getPriceDTO = new GetPriceDTO(brandId, productId, timestamp);
        GetPriceResponseDTO getPriceResponseDTO = pricesService.getPrice(getPriceDTO);

        Assert.assertNotNull(getPriceResponseDTO);
        Assert.assertEquals(price, getPriceResponseDTO.getPrice());
    }

    private GetPriceResponseDTO getGetPriceResponseDTO(String product, String brand, Double price) {
        GetPriceResponseDTO getPriceResponseDTO = new GetPriceResponseDTO();
        getPriceResponseDTO.setPrice(price);
        getPriceResponseDTO.setProduct(product);
        getPriceResponseDTO.setBrand(brand);
        return getPriceResponseDTO;
    }

    private PricesEntity getPricesEntity(Double price, String brand, String product) {
        PricesEntity pricesEntity = new PricesEntity();
        pricesEntity.setPrice(price);
        BrandsEntity brandsEntity = new BrandsEntity();
        brandsEntity.setName(brand);
        pricesEntity.setBrand(brandsEntity);
        ProductsEntity productsEntity = new ProductsEntity();
        productsEntity.setName(product);
        pricesEntity.setProduct(productsEntity);
        return pricesEntity;
    }
}
