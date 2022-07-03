package es.rbailen.sample.priceschallenge.infrastructure.adapters.input.rest;

import es.rbailen.sample.priceschallenge.application.ports.input.GetPriceUseCase;
import es.rbailen.sample.priceschallenge.domain.exception.PriceNotFound;
import es.rbailen.sample.priceschallenge.domain.model.Price;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.input.rest.data.response.PriceQueryResponse;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.input.rest.mapper.PriceRestMapper;
import es.rbailen.sample.priceschallenge.infrastructure.adapters.output.customizedexception.data.response.ExceptionResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "Prices")
public class PriceRestAdapter {

    private final GetPriceUseCase getPriceUseCase;

    private final PriceRestMapper priceRestMapper;

    @ApiOperation(value = "Return price for product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PriceQueryResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = PriceNotFound.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)})
    @GetMapping("brands/{brandId}/products/{productId}")
    public ResponseEntity<PriceQueryResponse> findProductPrice(
            @ApiParam(required = true, name = "brandId", value = "Foreign key de la cadena del grupo", example = "1")
            @PathVariable(name = "brandId") Long brandId,
            @ApiParam(required = true, name = "productId", value = "Identificador código de producto", example = "35455")
            @PathVariable(name = "productId") Long productId,
            @ApiParam(required = true, name = "date", value = "Fecha de aplicación", example = "2020-06-14T10:00:00")
            @RequestParam (value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date
    ) {
        log.info("Requesting price for product {} on {} with brand {}", productId, date, brandId);
        Price price = getPriceUseCase.getPrice(brandId, productId, date);
        log.info("Price for product {} on {} with brand {} is {} {}", productId, date, brandId, price.getPrice(), price.getCurrency());
        return new ResponseEntity<>(priceRestMapper.toPriceQueryResponse(price), HttpStatus.OK);
    }

}
