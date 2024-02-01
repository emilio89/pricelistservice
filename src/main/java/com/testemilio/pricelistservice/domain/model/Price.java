package com.testemilio.pricelistservice.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@IdClass(PriceId.class)
@Table(name="PRICES")
@Data
@NoArgsConstructor
public class Price implements Serializable {
    private static final long serialVersionUID = -4552391064110119266L;

    @Schema(description = "Id of the priceList.",
            example = "2", required = true)
    @Id
    @Column(name="PRICE_LIST")
    private Integer priceList;

    @Schema(description = "Id of the brand",
            example = "1", required = true)
    @Id
    @Column(name="BRAND_ID")
    private Integer brandId;

    @Schema(description = "Id of the product",
            example = "35455", required = true)
    @Id
    @Column(name="PRODUCT_ID")
    private Integer productId;

    @Schema(description = "Start date of the priceList",
            example = "2002-01-01-00.01.00", required = true)
    @Column(name="START_DATE")
    @Id
    private LocalDateTime startDate;

    @Schema(description = "End date of the priceList",
            example = "2024-01-31-23.59.59", required = true)
    @Column(name="END_DATE")
    @Id
    private LocalDateTime endDate;

    @Schema(description = "Priority of the priceList (higher value, more priority)",
            example = "1", required = true)
    @Column(name="PRIORITY")
    private Integer priority;

    @Schema(description = "Sale price",
            example = "25.45", required = true)
    @Column(name="PRICE")
    private Double price;

    @Schema(description = "Currency in ISO code",
            example = "EUR", required = true)
    @Column(name="CURR")
    private String currency;

}
