package com.klagan.inditex.service.mapper;

import com.klagan.inditex.model.Prices;
import com.klagan.inditex.persistence.entity.PricesEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PricesEntityToPricesMapper {

    public Prices map(PricesEntity pricesEntity) {
        return Prices.builder()
            .brandId(pricesEntity.getBrandId())
            .startDate(pricesEntity.getStartDate())
            .endDate(pricesEntity.getEndDate())
            .priceId(pricesEntity.getPriceList())
            .product(pricesEntity.getProductId())
            .priority(pricesEntity.getPriority())
            .price(pricesEntity.getPrice())
            .currency(pricesEntity.getCurrency())
            .build();
    }

    public PricesEntity map(Prices prices) {
        return PricesEntity.builder()
            .priceList(prices.getPriceId())
            .startDate(prices.getStartDate())
            .endDate(prices.getEndDate())
            .productId(prices.getProduct())
            .priority(prices.getPriority())
            .price(prices.getPrice())
            .currency(prices.getCurrency())
            .brandId(prices.getBrandId())
            .build();
    }

}
