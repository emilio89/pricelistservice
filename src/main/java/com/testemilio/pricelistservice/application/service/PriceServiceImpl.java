package com.testemilio.pricelistservice.application.service;

import com.testemilio.pricelistservice.application.port.service.PriceService;
import com.testemilio.pricelistservice.infraestructure.dto.PriceResponseDTO;
import com.testemilio.pricelistservice.infraestructure.persistence.PriceRepositoryAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceRepositoryAdapter priceRepositoryAdapter;

    @Override
    @Transactional(readOnly = true)
    public PriceResponseDTO getTopPriceFilterByBrandIdAndProductIdAndDate(Integer brandId, Integer productId, LocalDateTime date) {
        log.debug("Called method getTopPriceFilterByBrandIdAndProductIdAndDate with params => brandId: {} , productId: {} , date: {} ", brandId, productId, date);
        PageRequest pageRequestToGetToPriorityPrice = PageRequest.of(0, 1);
        PriceResponseDTO priceResponseDTO = priceRepositoryAdapter.findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates(brandId, productId, date, pageRequestToGetToPriorityPrice);
        log.debug("Exit method getTopPriceFilterByBrandIdAndProductIdAndDate with priceResponseDTO : {} ", priceResponseDTO);
        return priceResponseDTO;
    }

}
