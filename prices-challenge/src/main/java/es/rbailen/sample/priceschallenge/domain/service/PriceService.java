package es.rbailen.sample.priceschallenge.domain.service;

import es.rbailen.sample.priceschallenge.application.ports.input.GetPriceUseCase;
import es.rbailen.sample.priceschallenge.application.ports.output.PriceOutputPort;
import es.rbailen.sample.priceschallenge.domain.exception.PriceNotFound;
import es.rbailen.sample.priceschallenge.domain.model.Price;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Comparator;

@AllArgsConstructor
public class PriceService implements GetPriceUseCase {

    private final PriceOutputPort priceOutputPort;

    @Override
    public Price getPrice(Long brandId, Long productId, LocalDateTime date) {
        return priceOutputPort.getPrice(brandId, productId, date)
                .stream()
                .max(Comparator.comparing(Price::getPriority))
                .orElseThrow(() -> new PriceNotFound("Price not found"));
    }

}
