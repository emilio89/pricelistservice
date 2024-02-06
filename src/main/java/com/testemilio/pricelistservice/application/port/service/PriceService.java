package com.testemilio.pricelistservice.application.port.service;

import com.testemilio.pricelistservice.infraestructure.dto.PriceResponseDTO;

import java.time.LocalDateTime;

public interface PriceService {
    PriceResponseDTO getTopPriceFilterByBrandIdAndProductIdAndDate(Integer brandId, Integer productId, LocalDateTime date);
}
