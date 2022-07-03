package es.rbailen.sample.priceschallenge;

import es.rbailen.sample.priceschallenge.domain.model.Price;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static es.rbailen.sample.priceschallenge.domain.model.Currency.EUR;

public class PriceUtilsTest {

    public static LocalDateTime getLocalDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        return LocalDateTime.of(LocalDate.of(year, month, dayOfMonth), LocalTime.of(hour, minute, second));
    }

    public static Price example1() {
        return Price
                .builder()
                    .brandId(1L)
                    .startDate(getLocalDateTime(2020, 6, 14, 0, 0, 0))
                    .endDate(getLocalDateTime(2020, 12, 31, 23, 59, 59))
                    .priceList(1)
                    .productId(35455L)
                    .priority(0)
                    .price(BigDecimal.valueOf(35.50))
                    .currency(EUR)
                .build();
    }

    public static Price example2() {
        return Price
                .builder()
                    .brandId(1L)
                    .startDate(getLocalDateTime(2020, 6, 14, 15, 0, 0))
                    .endDate(getLocalDateTime(2020, 6, 14, 18, 30, 0))
                    .priceList(2)
                    .productId(35455L)
                    .priority(1)
                    .price(BigDecimal.valueOf(25.45))
                    .currency(EUR)
                .build();
    }

    public static Price example3() {
        return Price
                .builder()
                    .brandId(1L)
                    .startDate(getLocalDateTime(2020, 6, 15, 0, 0, 0))
                    .endDate(getLocalDateTime(2020, 6, 15, 11, 0, 0))
                    .priceList(3)
                    .productId(35455L)
                    .priority(1)
                    .price(BigDecimal.valueOf(30.5))
                    .currency(EUR)
                .build();
    }

    public static Price example4() {
        return Price
                .builder()
                    .brandId(1L)
                    .startDate(getLocalDateTime(2020, 6, 15, 16, 0, 0))
                    .endDate(getLocalDateTime(2020, 12, 31, 23, 59, 59))
                    .priceList(4)
                    .productId(35455L)
                    .priority(1)
                    .price(BigDecimal.valueOf(38.95))
                    .currency(EUR)
                .build();
    }

}
