package com.testemilio.pricelistservice.infraestructure.persistence;

import com.testemilio.pricelistservice.domain.model.Price;
import com.testemilio.pricelistservice.domain.model.PriceId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceJpaRepository extends JpaRepository<Price, PriceId> {
    @Query("SELECT p FROM Price p WHERE p.id.brandId = :brandId " +
            " AND p.id.productId = :productId " +
            " AND :date BETWEEN p.id.startDate AND p.id.endDate " +
            " ORDER BY p.priority DESC")
    List<Price> findTopPriorityPriceWithBrandIdProductIdAndIsBetweenDates(@Param("brandId") Integer brandId, @Param("productId") Integer productId, @Param("date") LocalDateTime date, Pageable pageable);

}
