package es.rbailen.sample.priceschallenge.infrastructure.adapters.input.rest.mapper;

import es.rbailen.sample.priceschallenge.domain.model.Price;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.input.rest.data.response.PriceQueryResponse;
import org.mapstruct.Mapper;

@Mapper
public interface PriceRestMapper {

    PriceQueryResponse toPriceQueryResponse(Price price);

}
