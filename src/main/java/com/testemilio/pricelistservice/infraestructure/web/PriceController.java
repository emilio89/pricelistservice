package com.testemilio.pricelistservice.infraestructure.web;

import com.testemilio.pricelistservice.application.api.PriceApi;
import com.testemilio.pricelistservice.application.port.service.PriceService;
import com.testemilio.pricelistservice.infraestructure.dto.PriceResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PriceController implements PriceApi {

    @Autowired
    PriceService priceService;
    @GetMapping(value="priceList/{brandId}/{productId}/{date}" )
    public ResponseEntity<PriceResponseDTO> getPriceListWithFilters(
            @PathVariable(value="brandId") Integer brandId,
            @PathVariable(value="productId") Integer productId,
            @PathVariable(value="date") String date) {
        log.info("getPriceListWithFilters called ");
        log.info("Parameters received => brandId: {} , productId: {} , date: {} ", brandId, productId, date);
        PriceResponseDTO priceResponse = priceService.getTopPriceFilterByBrandIdAndProductIdAndDate(brandId, productId, date);
        if (priceResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            log.info("Response getPriceListWithFilters => brandId: {}  ", priceResponse);
            return ResponseEntity.status(HttpStatus.OK).body(priceResponse);
        }
    }

}
