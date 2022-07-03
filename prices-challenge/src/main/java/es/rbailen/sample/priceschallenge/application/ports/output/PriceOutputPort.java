package es.rbailen.sample.priceschallenge.application.ports.output;

import es.rbailen.sample.priceschallenge.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceOutputPort {

    List<Price> getPrice(Long brandId, Long productId, LocalDateTime date);

}
