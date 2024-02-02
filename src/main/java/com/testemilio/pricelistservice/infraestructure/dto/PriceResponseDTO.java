package com.testemilio.pricelistservice.infraestructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponseDTO {
    @Schema(description = "PriceList Id",
            example = "2")
    private Integer priceList;

    @Schema(description = "Brand id",
            example = "1")
    private Integer brandId;

    @Schema(description = "Product id",
            example = "35455")
    private Integer productId;

    @Schema(description = "Start date of the priceList",
            example = "2020-06-14-00.00.00")
    private LocalDateTime startDate;

    @Schema(description = "End date of the priceList.",
            example = "2020-12-31-23.59.59")
    private LocalDateTime endDate;

    @Schema(description = "Sale price",
            example = "35.50")
    private Double price;

}
