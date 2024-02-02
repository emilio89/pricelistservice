package com.testemilio.pricelistservice.application.port.service;

import com.testemilio.pricelistservice.infraestructure.dto.PriceResponseDTO;

public interface PriceService {
    PriceResponseDTO getTopPriceFilterByBrandIdAndProductIdAndDate(Integer brandId, Integer productId, String date);
}
