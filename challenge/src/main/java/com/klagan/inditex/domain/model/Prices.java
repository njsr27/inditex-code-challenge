package com.klagan.inditex.domain.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class Prices {
    Integer brandId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Integer priceId;
    Integer product;
    Integer priority;
    BigDecimal price;
    String currency;
}
