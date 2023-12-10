package com.klagan.inditex.domain.service.mapper;

import com.klagan.inditex.adapters.controller.response.GetPricesDetailsResponse;
import com.klagan.inditex.domain.model.Prices;
import com.klagan.inditex.domain.service.formatter.DateFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PricesToPricesResponseMapper {

    private final DateFormatter dateFormatter;

    public GetPricesDetailsResponse map(Prices prices) {
        return GetPricesDetailsResponse.builder()
            .productId(prices.getProduct())
            .brandId(prices.getBrandId())
            .priceId(prices.getPriceId())
            .priceToApply(prices.getPrice())
            .startDate(dateFormatter.format(prices.getStartDate()))
            .endDate(dateFormatter.format(prices.getEndDate()))
            .build();
    }
}
