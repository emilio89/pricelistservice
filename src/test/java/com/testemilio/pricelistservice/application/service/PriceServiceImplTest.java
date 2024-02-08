package com.testemilio.pricelistservice.application.service;

import com.testemilio.pricelistservice.domain.exception.PriceNotFoundException;
import com.testemilio.pricelistservice.infraestructure.dto.PriceResponseDTO;
import com.testemilio.pricelistservice.infraestructure.persistence.PriceRepositoryAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {
    @Mock
    PriceRepositoryAdapter priceRepositoryAdapter;

    @InjectMocks
    private PriceServiceImpl priceService;

    private PriceResponseDTO initializePriceResponseDTO() {
        LocalDateTime startDate = LocalDateTime.MIN;
        LocalDateTime endDate = LocalDateTime.MAX;
        Integer priceList = 1;
        Integer brandId = 1;
        Integer productId = 1;
        PriceResponseDTO priceResponseDTO = new PriceResponseDTO();
        priceResponseDTO.setPriceList(priceList);
        priceResponseDTO.setStartDate(startDate);
        priceResponseDTO.setEndDate(endDate);
        priceResponseDTO.setBrandId(brandId);
        priceResponseDTO.setProductId(productId);
        priceResponseDTO.setPrice(22.5);
        return priceResponseDTO;
    }
    @Test
    @DisplayName("Test successful retrieval of top price")
    void testGetTopPriceSuccess() {
        LocalDateTime date = LocalDateTime.now();
        Integer brandId = 1;
        Integer productId = 1;
        PriceResponseDTO priceResponseDTO = initializePriceResponseDTO();
        when(priceRepositoryAdapter.findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates(eq(brandId), eq(productId), any(LocalDateTime.class), any(PageRequest.class)))
                .thenReturn(priceResponseDTO);
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

