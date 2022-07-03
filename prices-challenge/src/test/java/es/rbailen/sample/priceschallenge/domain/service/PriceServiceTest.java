package es.rbailen.sample.priceschallenge.domain.service;

import es.rbailen.sample.priceschallenge.application.ports.input.GetPriceUseCase;
import es.rbailen.sample.priceschallenge.application.ports.output.PriceOutputPort;
import es.rbailen.sample.priceschallenge.domain.exception.PriceNotFound;
import es.rbailen.sample.priceschallenge.domain.model.Price;
import es.rbailen.sample.priceschallenge.PriceUtilsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PriceServiceTest {

    private GetPriceUseCase getPriceUseCase;
    private PriceOutputPort priceOutputPort;

    private final static Long BRAND_ID = 1L;
    private final static Long PRODUCT_ID = 35455L;

    @BeforeEach
    public void setUp() {
        priceOutputPort = mock(PriceOutputPort.class);
        getPriceUseCase = new PriceService(priceOutputPort);
    }

    @Test
    public void getPriceForDay14And10Hours() {
        LocalDateTime date = PriceUtilsTest.getLocalDateTime(2020, 6, 14, 10, 0, 0);

        Price expected = PriceUtilsTest.example1();
        List<Price> expectedPrices = Arrays.asList(expected);
        when(priceOutputPort.getPrice(eq(BRAND_ID), eq(PRODUCT_ID), eq(date))).thenReturn(expectedPrices);

        verifyPrice(date, expected);
    }

    @Test
    public void getPriceForDay14And16Hours() {
        LocalDateTime date = PriceUtilsTest.getLocalDateTime(2020, 6, 14, 16, 0, 0);

        Price expected = PriceUtilsTest.example2();
        List<Price> expectedPrices = Arrays.asList(PriceUtilsTest.example1(), expected);
        when(priceOutputPort.getPrice(eq(BRAND_ID), eq(PRODUCT_ID), eq(date))).thenReturn(expectedPrices);

        verifyPrice(date, expected);
    }

    @Test
    public void getPriceForDay14And21Hours() {
        LocalDateTime date = PriceUtilsTest.getLocalDateTime(2020, 6, 14, 21, 0, 0);

        Price expected = PriceUtilsTest.example1();
        List<Price> expectedPrices = Arrays.asList(expected);
        when(priceOutputPort.getPrice(eq(BRAND_ID), eq(PRODUCT_ID), eq(date))).thenReturn(expectedPrices);

        verifyPrice(date, expected);
    }

    @Test
    public void getPriceForDay15And10Hours() {
        LocalDateTime date = PriceUtilsTest.getLocalDateTime(2020, 6, 15, 10, 0, 0);

        Price expected = PriceUtilsTest.example3();
        List<Price> expectedPrices = Arrays.asList(PriceUtilsTest.example1(), expected);
        when(priceOutputPort.getPrice(eq(BRAND_ID), eq(PRODUCT_ID), eq(date))).thenReturn(expectedPrices);

        verifyPrice(date, expected);
    }

    @Test
    public void getPriceForDay16And21Hours() {
        LocalDateTime date = PriceUtilsTest.getLocalDateTime(2020, 6, 15, 10, 0, 0);

        Price expected = PriceUtilsTest.example4();
        List<Price> expectedPrices = Arrays.asList(PriceUtilsTest.example1(), expected);
        when(priceOutputPort.getPrice(eq(BRAND_ID), eq(PRODUCT_ID), eq(date))).thenReturn(expectedPrices);

        Price result = getPriceUseCase.getPrice(BRAND_ID, PRODUCT_ID, date);
        verifyPrice(date, expected);
    }

    @Test
    public void getProductNotFoundException() {
        LocalDateTime date = LocalDateTime.of(LocalDate.of(2020, 6, 15), LocalTime.of(10, 0, 0));

        when(priceOutputPort.getPrice(eq(BRAND_ID), eq(PRODUCT_ID), eq(date))).thenReturn(Arrays.asList());

        assertThrows(PriceNotFound.class, () -> getPriceUseCase.getPrice(BRAND_ID, PRODUCT_ID, date));
    }

    private void verifyPrice(LocalDateTime date, Price expected) {
        Price result = getPriceUseCase.getPrice(BRAND_ID, PRODUCT_ID, date);
        assertEquals(expected, result);
    }

}
