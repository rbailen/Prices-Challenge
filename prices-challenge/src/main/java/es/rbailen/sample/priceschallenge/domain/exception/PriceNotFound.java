package es.rbailen.sample.priceschallenge.domain.exception;

public class PriceNotFound extends RuntimeException {

    public PriceNotFound(String message) {
        super(message);
    }

}
