package es.rbailen.sample.priceschallenge.infrastructure.adapters.input.rest;

import es.rbailen.sample.priceschallenge.application.ports.input.GetPriceUseCase;
import es.rbailen.sample.priceschallenge.PriceUtilsTest;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.input.rest.mapper.PriceRestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class PriceRestAdapterTest {

    private GetPriceUseCase getPriceUseCase;
    private PriceRestMapper priceRestMapper;

    private MockMvc mockMvc;

    private final static Long BRAND_ID = 1L;
    private final static Long PRODUCT_ID = 35455L;

    @BeforeEach
    public void setUp() {
        getPriceUseCase = mock(GetPriceUseCase.class);
        priceRestMapper = mock(PriceRestMapper.class);
        PriceRestAdapter priceRestAdapter = new PriceRestAdapter(getPriceUseCase, priceRestMapper);

        mockMvc = standaloneSetup(priceRestAdapter).build();
    }

    @Test
    public void getPriceStatusCode200() throws Exception {
        LocalDateTime date = PriceUtilsTest.getLocalDateTime(2020, 6, 14, 10, 0, 0);
        when(getPriceUseCase.getPrice(eq(BRAND_ID), eq(PRODUCT_ID), eq(date))).thenReturn(PriceUtilsTest.example1());

        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/prices/brands/1/products/35455?date=2020-06-14T10:00:00"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPriceStatusCode400() throws Exception {
        LocalDateTime date = PriceUtilsTest.getLocalDateTime(2020, 6, 14, 10, 0, 0);
        when(getPriceUseCase.getPrice(eq(BRAND_ID), eq(PRODUCT_ID), eq(date))).thenReturn(PriceUtilsTest.example1());

        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/prices/brands/1/products/35455"))
                .andExpect(status().isBadRequest());
    }

}
