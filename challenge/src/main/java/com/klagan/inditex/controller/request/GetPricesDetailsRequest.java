package com.klagan.inditex.controller.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetPricesDetailsRequest {
    String date;
    Integer productId;
    Integer brandId;
}
