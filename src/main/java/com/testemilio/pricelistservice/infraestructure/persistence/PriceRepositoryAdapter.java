package com.testemilio.pricelistservice.infraestructure.persistence;

import com.testemilio.pricelistservice.application.port.repository.PriceDomainRepository;
import com.testemilio.pricelistservice.domain.exception.PriceNotFoundException;
import com.testemilio.pricelistservice.domain.model.Price;
import com.testemilio.pricelistservice.infraestructure.dto.PriceResponseDTO;
import com.testemilio.pricelistservice.infraestructure.mapper.PriceMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PriceRepositoryAdapter implements PriceDomainRepository {
    private final PriceJpaRepository priceJpaRepository;

    public PriceRepositoryAdapter(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }

    @Override
    public PriceResponseDTO findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates(Integer brandId, Integer productId, LocalDateTime date, Pageable pageable) {
        List <Price> priceList = priceJpaRepository.findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates(brandId, productId, date, pageable);
        List <PriceResponseDTO> priceResponseDTOList = PriceMapper.INSTANCE.pricesToPriceResponseDTOs(priceList);

        return priceResponseDTOList.stream()
                .findFirst()
                .orElseThrow(PriceNotFoundException::new);
    }

}
