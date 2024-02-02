package com.testemilio.pricelistservice.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PriceId implements Serializable {

    @Serial
    private static final long serialVersionUID = 3565353301766953423L;

    @Schema(description = "Id of the priceList.",
            example = "2")
    @Column(name="PRICE_LIST")
    private Integer priceList;
    @Schema(description = "Id of the brand",
            example = "1")
    @Column(name="BRAND_ID")
    private Integer brandId;
    @Schema(description = "Id of the product",
            example = "35455")
    @Column(name="PRODUCT_ID")
    private Integer productId;

    @Schema(description = "Start date of the priceList",
            example = "2002-01-01-00.01.00")
    @Column(name="START_DATE")
    private LocalDateTime startDate;

    @Schema(description = "End date of the priceList",
            example = "2024-01-31-23.59.59")
    @Column(name="END_DATE")
    private LocalDateTime endDate;

}
