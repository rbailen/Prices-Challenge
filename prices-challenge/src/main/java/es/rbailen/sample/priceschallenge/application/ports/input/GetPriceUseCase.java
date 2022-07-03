package es.rbailen.sample.priceschallenge.application.ports.input;

import es.rbailen.sample.priceschallenge.domain.model.Price;

import java.time.LocalDateTime;

public interface GetPriceUseCase {

    Price getPrice(Long brandId, Long productId, LocalDateTime date);

}
