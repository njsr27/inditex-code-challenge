package com.klagan.inditex.controller.response;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class GetPricesDetailsResponse {
    Integer productId;
    Integer brandId;
    Integer priceId;
    BigDecimal priceToApply;
    String startDate;
    String endDate;
}
