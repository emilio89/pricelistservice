package com.testemilio.pricelistservice.application.port.repository;

import com.testemilio.pricelistservice.domain.model.Price;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates (@Param("brandId") Integer brandId, @Param("productId") Integer productId, @Param("date") LocalDateTime date, Pageable pageable);

}
