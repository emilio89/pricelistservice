package com.testemilio.pricelistservice.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="PRICES")
@Data
@NoArgsConstructor
public class Price implements Serializable {
    @Serial
    private static final long serialVersionUID = -4552391064110119266L;

    @EmbeddedId
    private PriceId id;

    @Schema(description = "Priority of the priceList (higher value, more priority)",
            example = "1")
    @Column(name="PRIORITY")
    private Integer priority;

    @Schema(description = "Sale price",
            example = "25.45")
    @Column(name="PRICE")
    private Double finalPrice;

    @Schema(description = "Currency in ISO code",
            example = "EUR")
    @Column(name="CURR")
    private String currency;

}
