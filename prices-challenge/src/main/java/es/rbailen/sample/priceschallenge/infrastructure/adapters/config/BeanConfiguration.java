package es.rbailen.sample.priceschallenge.infrastructure.adapters.config;

import es.rbailen.sample.priceschallenge.domain.service.PriceService;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.PricePersistenceAdapter;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.mapper.PricePersistenceMapper;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.repository.PriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PricePersistenceAdapter pricePersistenceAdapter(PriceRepository priceRepository, PricePersistenceMapper pricePersistenceMapper) {
        return new PricePersistenceAdapter(priceRepository, pricePersistenceMapper);
    }

    @Bean
    public PriceService priceService(PricePersistenceAdapter pricePersistenceAdapter) {
        return new PriceService(pricePersistenceAdapter);
    }

}
