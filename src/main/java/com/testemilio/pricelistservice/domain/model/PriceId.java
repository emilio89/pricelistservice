package com.testemilio.pricelistservice.domain.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceId implements Serializable {

    private static final long serialVersionUID = 3565353301766953423L;

    private Integer priceList;
    private Integer productId;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
