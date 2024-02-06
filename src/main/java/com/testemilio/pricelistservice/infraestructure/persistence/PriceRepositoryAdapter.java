package com.testemilio.pricelistservice.infraestructure.persistence;

import com.testemilio.pricelistservice.application.port.repository.PriceDomainRepository;
import com.testemilio.pricelistservice.domain.exception.PriceNotFoundException;
import com.testemilio.pricelistservice.domain.model.Price;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class PriceRepositoryAdapter implements PriceDomainRepository {
    private final PriceJpaRepository priceJpaRepository;

    public PriceRepositoryAdapter(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }

    @Override
    public List<Price> findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates(Integer brandId, Integer productId, LocalDateTime date, Pageable pageable) {
        return Optional.ofNullable(priceJpaRepository.findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates(brandId, productId, date, pageable))
                .filter(prices -> !prices.isEmpty())
                .orElseThrow(() -> new PriceNotFoundException());
    }

}
