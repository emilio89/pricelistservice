package com.testemilio.pricelistservice.application.service;

import com.testemilio.pricelistservice.application.port.repository.PriceRepository;
import com.testemilio.pricelistservice.domain.exception.PriceNotFoundException;
import com.testemilio.pricelistservice.domain.model.Price;
import com.testemilio.pricelistservice.domain.model.PriceId;
import com.testemilio.pricelistservice.infraestructure.dto.PriceResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {
    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(priceService, "dateFormatPattern", "yyyy-MM-dd-HH.mm.ss");
    }

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
        String date = "2024-02-02-10.00.00";
        Integer brandId = 1;
        Integer productId = 1;
        Price price = initializePrice();
        List<Price> mockPriceList = Collections.singletonList(price);
        when(priceRepository.findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates(eq(brandId), eq(productId), any(LocalDateTime.class), any(PageRequest.class)))
                .thenReturn(mockPriceList);
        PriceResponseDTO result = priceService.getTopPriceFilterByBrandIdAndProductIdAndDate(brandId, productId, date);
        Assertions.assertNotNull(result);
        verify(priceRepository, times(1)).findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates(eq(brandId), eq(productId), any(LocalDateTime.class), any(PageRequest.class));
    }

    @Test
    @DisplayName("Test no price found")
    void testNoPriceFound() {
        when(priceRepository.findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates(anyInt(), anyInt(), any(LocalDateTime.class), any(PageRequest.class)))
                .thenReturn(Collections.emptyList());

        PriceResponseDTO result = priceService.getTopPriceFilterByBrandIdAndProductIdAndDate(1, 1, "2024-02-02-10.00.00");

        Assertions.assertNull(result);
    }

    @Test
    @DisplayName("Test exception handling for invalid date format")
    void testExceptionHandlingForInvalidDate() {
        Exception exception = Assertions.assertThrows(PriceNotFoundException.class, () ->
                priceService.getTopPriceFilterByBrandIdAndProductIdAndDate(1, 1, "invalid-date"));

        Assertions.assertTrue(exception.getMessage().contains("Unable to retrieve Price"));
    }

}

