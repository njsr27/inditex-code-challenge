package com.klagan.inditex.persistence.repository;

import com.klagan.inditex.persistence.entity.PricesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PricesRepositoryCustom {

    @Query(value = "SELECT p from PricesEntity p where :date BETWEEN p.startDate AND p.endDate " +
        "AND p.productId = :productId " +
        "AND p.brandId = :brandId " +
        "ORDER BY p.priority DESC " +
        "LIMIT 1")
    PricesEntity getPricesDetails(
        @Param("date") LocalDateTime date,
        @Param("productId") Integer productId,
        @Param("brandId") Integer brandId
    );

}
