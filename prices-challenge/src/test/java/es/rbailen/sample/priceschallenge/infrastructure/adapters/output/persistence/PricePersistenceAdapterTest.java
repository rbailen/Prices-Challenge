package es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence;

import es.rbailen.sample.priceschallenge.application.ports.output.PriceOutputPort;
import es.rbailen.sample.priceschallenge.domain.model.Price;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.entity.PriceEntity;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.mapper.PricePersistenceMapper;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static es.rbailen.sample.priceschallenge.domain.model.Currency.EUR;
import static java.math.BigDecimal.ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PricePersistenceAdapterTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PriceRepository priceRepository;

    private PricePersistenceMapper pricePersistenceMapper;
    private PriceOutputPort priceOutputPort;

    private LocalDateTime date;

    private final static Long BRAND_ID = 1L;
    private final static Long PRODUCT_ID = 35455L;

    @BeforeEach
    public void setUp() {
        date = LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0, 0));
        pricePersistenceMapper = Mappers.getMapper(PricePersistenceMapper.class);
        priceOutputPort = new PricePersistenceAdapter(priceRepository, pricePersistenceMapper);

        givenPrice();
    }

    @Test
    public void getPriceFound() {
        List<Price> prices = priceOutputPort.getPrice(BRAND_ID, PRODUCT_ID, date);
        assertEquals(1, prices.size());
        assertEquals(0, prices.stream().findFirst().map(Price::getPriority).get());
        assertEquals(ONE, prices.stream().findFirst().map(Price::getPrice).get());
    }

    @Test
    public void getPriceNotFound_1() {
        List<Price> prices = priceOutputPort.getPrice(BRAND_ID, 10L, date);
        assertEquals(0, prices.size());
    }

    @Test
    public void getPriceNotFound_2() {
        List<Price> prices = priceOutputPort.getPrice(2L, PRODUCT_ID, date);
        assertEquals(0, prices.size());
    }

    private void givenPrice() {
        PriceEntity priceEntity = PriceEntity
                .builder()
                    .brandId(BRAND_ID)
                    .startDate(date.minusDays(1))
                    .endDate(date.plusMonths(5))
                    .priceList(1)
                    .productId(PRODUCT_ID)
                    .priority(0)
                    .price(ONE)
                    .currency(EUR)
                    .creationDate(LocalDateTime.now())
                .build();

        entityManager.persist(priceEntity);
        entityManager.flush();
    }

}
