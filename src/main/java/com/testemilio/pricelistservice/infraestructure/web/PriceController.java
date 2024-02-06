package com.testemilio.pricelistservice.infraestructure.web;

import com.testemilio.pricelistservice.application.port.service.PriceService;
import com.testemilio.pricelistservice.infraestructure.dto.PriceResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@Slf4j
@Tag(name = "PriceList")
public class PriceController {

    @Value("${date.format.pattern}")
    private String dateFormatPattern;

    @Autowired
    PriceService priceService;


    @Operation(
            summary = "Find price for a product using the brandId, productId and date",
            description = "Find price for a product using the brandId, productId and date",
            tags = {"PriceList"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = PriceResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "price not found not found")})
    @GetMapping(value = "pricelist/brands/{brandId}/products/{productId}")
    public ResponseEntity<PriceResponseDTO> getPriceListWithFilters(
            @Parameter(
                    description = "Id of the brand.",
                    example = "1",
                    required = true)
            @PathVariable(value = "brandId") Integer brandId,
            @Parameter(
                    description = "Id of the product.",
                    example = "35455",
                    required = true)
            @PathVariable(value = "productId") Integer productId,
            @Parameter(
                    description = "Date of the request. Format: yyyy-MM-dd-HH.mm.ss",
                    example = "2020-06-14-10.00.00",
                    required = true)
            @RequestParam(value = "date") String date) {
        log.info("getPriceListWithFilters called ");
        log.info("Parameters received => brandId: {} , productId: {} , date: {} ", brandId, productId, date);
        LocalDateTime localDateTime = parseToLocalDateTime(date);
        PriceResponseDTO priceResponse = priceService.getTopPriceFilterByBrandIdAndProductIdAndDate(brandId, productId, localDateTime);
        log.info("Response getPriceListWithFilters => brandId: {}  ", priceResponse);
        return ResponseEntity.status(HttpStatus.OK).body(priceResponse);

    }

    private LocalDateTime parseToLocalDateTime(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern);
        return LocalDateTime.parse(date, formatter);
    }

}
