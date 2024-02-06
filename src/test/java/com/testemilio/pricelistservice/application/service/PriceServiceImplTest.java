package com.testemilio.pricelistservice.application.service;

import com.testemilio.pricelistservice.application.port.repository.PriceDomainRepository;
import com.testemilio.pricelistservice.domain.exception.InvalidDateFormatException;
import com.testemilio.pricelistservice.domain.exception.PriceException;
import com.testemilio.pricelistservice.domain.exception.PriceNotFoundException;
import com.testemilio.pricelistservice.domain.model.Price;
import com.testemilio.pricelistservice.domain.model.PriceId;
import com.testemilio.pricelistservice.infraestructure.dto.PriceResponseDTO;
import com.testemilio.pricelistservice.infraestructure.persistence.PriceRepositoryAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {
    @Mock
    PriceRepositoryAdapter priceRepositoryAdapter;

    @InjectMocks
    private PriceServiceImpl priceService;

    private Price initializePrice() {
        LocalDateTime startDate = LocalDateTime.MIN;
        LocalDateTime endDate = LocalDateTime.MAX;
        Integer priceList = 1;
        Integer brandId = 1;
        Integer productId = 1;
        PriceId priceId = new PriceId(priceList, brandId, productId, startDate, endDate );
        Price price = new Price();
        price.setId(priceId);
        price.setPriority(1);
        price.setFinalPrice(22.5);
        price.setCurrency("EUR");
        return price;
    }
    @Test
    @DisplayName("Test successful retrieval of top price")
    void testGetTopPriceSuccess() {
        LocalDateTime date = LocalDateTime.now();
        Integer brandId = 1;
        Integer productId = 1;
        Price price = initializePrice();
        List<Price> mockPriceList = Collections.singletonList(price);
        when(priceRepositoryAdapter.findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates(eq(brandId), eq(productId), any(LocalDateTime.class), any(PageRequest.class)))
                .thenReturn(mockPriceList);
        PriceResponseDTO result = priceService.getTopPriceFilterByBrandIdAndProductIdAndDate(brandId, productId, date);
        Assertions.assertNotNull(result);
        verify(priceRepositoryAdapter, times(1)).findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates(eq(brandId), eq(productId), any(LocalDateTime.class), any(PageRequest.class));
    }

    @Test
    @DisplayName("Test no price found")
    void testNoPriceFound() {
        LocalDateTime date = LocalDateTime.now();
        when(priceRepositoryAdapter.findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates(anyInt(), anyInt(), any(LocalDateTime.class), any(PageRequest.class)))
                .thenThrow(PriceNotFoundException.class);

        Assertions.assertThrows(PriceNotFoundException.class, () ->
                priceService.getTopPriceFilterByBrandIdAndProductIdAndDate(1, 1, date));

    }


}

