import com.inditex.InditexSbApplication;
import com.inditex.core.Constants.Constants;
import com.inditex.core.prices.dto.GetPriceResponseDTO;
import com.inditex.core.prices.endpoint.PricesEndpoint;
import com.inditex.core.prices.service.PricesService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = InditexSbApplication.class)
@WebMvcTest(PricesEndpoint.class)
public class PricesEndpointTest {
    @MockBean
    private PricesService pricesService;

    @Autowired
    private MockMvc mockMvc;
    final private Integer RANDOM_STRING_LENGTH = 10;
    final private String BASE_API_PATH = "/prices";
    final private String GET_PRICES_API = "/getPrices";

    @Test
    public void getPrice() throws Exception {
        String brand = RandomStringUtils.random(RANDOM_STRING_LENGTH);
        String product = RandomStringUtils.random(RANDOM_STRING_LENGTH);
        Double price = Math.random();

        GetPriceResponseDTO serviceResponse = getPriceResponseDTO(brand, product, price);
        Mockito.when(pricesService.getPrice(Mockito.any())).thenReturn(serviceResponse);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(getRandomUrl(BASE_API_PATH, GET_PRICES_API));

        mockMvc.perform(builder)
                .andExpect(result -> HttpStatus.OK.equals(result.getResponse().getStatus()))
                .andExpect(result -> resultContainInputInfo(result.getResponse().getContentAsString(), brand, product, price));

    }

    private boolean resultContainInputInfo(String contentAsString, String brand, String product, Double price) {
        return contentAsString.contains(brand) && contentAsString.contains(product) && contentAsString.contains(price.toString());
    }

    private String getRandomUrl(String baseApiPath, String getPricesApi) {
        return baseApiPath + Constants.SLASH + getPricesApi + Constants.SLASH + Math.random() + Constants.SLASH + Math.random() + Constants.SLASH + RandomStringUtils.random(5);
    }

    private GetPriceResponseDTO getPriceResponseDTO(String brand, String product, Double price) {
        GetPriceResponseDTO responseDTO = new GetPriceResponseDTO();
        responseDTO.setBrand(brand);
        responseDTO.setProduct(product);
        responseDTO.setPrice(price);
        return responseDTO;

    }
}
