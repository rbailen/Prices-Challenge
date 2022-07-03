package es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence;

import es.rbailen.sample.priceschallenge.application.ports.output.PriceOutputPort;
import es.rbailen.sample.priceschallenge.domain.model.Price;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.entity.PriceEntity;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.mapper.PricePersistenceMapper;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.repository.PriceRepository;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.spec.PriceSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PricePersistenceAdapter extends PriceSpec<PriceEntity> implements PriceOutputPort {

    private final PriceRepository priceRepository;

    private final PricePersistenceMapper pricePersistenceMapper;

    @Override
    public List<Price> getPrice(Long brandId, Long productId, LocalDateTime date) {
        Specification<PriceEntity> specification = Specification
                .where(equals("productId", productId))
                .and(equals("brandId", brandId))
                .and(lessThanOrEqualTo("startDate", date))
                .and(greaterThanOrEqualTo("endDate", date));

        return priceRepository.findAll(specification)
                .stream()
                .map(priceEntity -> pricePersistenceMapper.toPrice(priceEntity))
                .collect(Collectors.toList());
    }

}
