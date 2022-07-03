package es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.mapper;

import es.rbailen.sample.priceschallenge.domain.model.Price;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PricePersistenceMapper {

    Price toPrice(PriceEntity priceEntity);

}
