package com.testemilio.pricelistservice.infraestructure.web;

import com.testemilio.pricelistservice.application.port.service.PriceService;
import com.testemilio.pricelistservice.infraestructure.dto.PriceResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @Test
    @DisplayName("Test when PriceService returns a PriceResponseDTO")
    void testGetPriceListWithValidResponse() {
        Integer brandId = 1;
        Integer productId = 1;
        String date = "2024-02-02T10:00:00";
        PriceResponseDTO mockResponse = new PriceResponseDTO(1, 1, 35455, LocalDateTime.MIN, LocalDateTime.MAX,  35.50);

        when(priceService.getTopPriceFilterByBrandIdAndProductIdAndDate(brandId, productId, date)).thenReturn(mockResponse);

        ResponseEntity<PriceResponseDTO> response = priceController.getPriceListWithFilters(brandId, productId, date);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    @DisplayName("Test when PriceService returns null")
    void testGetPriceListWithNullResponse() {
        Integer brandId = 1;
        Integer productId = 1;
        String date = "2024-02-02T10:00:00";

        when(priceService.getTopPriceFilterByBrandIdAndProductIdAndDate(brandId, productId, date)).thenReturn(null);

        ResponseEntity<PriceResponseDTO> response = priceController.getPriceListWithFilters(brandId, productId, date);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
