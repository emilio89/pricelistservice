package com.testemilio.pricelistservice.application.port.repository;

import com.testemilio.pricelistservice.infraestructure.dto.PriceResponseDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PriceDomainRepository {
    PriceResponseDTO findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates (Integer brandId, Integer productId, LocalDateTime date, Pageable pageable);

}
