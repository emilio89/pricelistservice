package com.testemilio.pricelistservice.application.service;

import com.testemilio.pricelistservice.application.port.repository.PriceRepository;
import com.testemilio.pricelistservice.domain.exception.InvalidDateFormatException;
import com.testemilio.pricelistservice.domain.exception.PriceNotFoundException;
import com.testemilio.pricelistservice.domain.model.Price;
import com.testemilio.pricelistservice.application.port.service.PriceService;
import com.testemilio.pricelistservice.infraestructure.dto.PriceResponseDTO;
import com.testemilio.pricelistservice.infraestructure.mapper.PriceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@Slf4j
public class PriceServiceImpl implements PriceService {

    @Value("${date.format.pattern}")
    private String dateFormatPattern;
    @Autowired
    PriceRepository priceRepository;

    @Override
    @Transactional(readOnly = true)
    public PriceResponseDTO getTopPriceFilterByBrandIdAndProductIdAndDate(Integer brandId, Integer productId, String date) {
        log.debug("Called method getTopPriceFilterByBrandIdAndProductIdAndDate with params => brandId: {} , productId: {} , date: {} ", brandId, productId, date);
        PriceResponseDTO priceResponseDTO = null;
        try {
            LocalDateTime localDateTime = parseToLocalDateTime(date);
            PageRequest pageRequestToGetToPriorityPrice = PageRequest.of(0, 1);
            List<Price> priceList = priceRepository.findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates(brandId, productId, localDateTime, pageRequestToGetToPriorityPrice);
            if (priceList != null && !priceList.isEmpty()) {
                priceResponseDTO = PriceMapper.INSTANCE.priceToPriceResponseDTO(priceList.get(0));
            } else {
                log.warn("No found price in database with params => brandId: {} , productId: {} , date: {} ", brandId, productId, date);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format: " + date, e);
        } catch (Exception e) {
            throw new PriceNotFoundException("Unable to retrieve Price ", e);
        }
        log.debug("Exit method getTopPriceFilterByBrandIdAndProductIdAndDate with priceResponseDTO : {} ", priceResponseDTO);
        return priceResponseDTO;
    }

    private LocalDateTime parseToLocalDateTime(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern);
        return LocalDateTime.parse(date, formatter);
    }
}
