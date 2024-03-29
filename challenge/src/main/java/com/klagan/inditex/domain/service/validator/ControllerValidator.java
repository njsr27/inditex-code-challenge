package com.klagan.inditex.domain.service.validator;

import com.klagan.inditex.adapters.controller.request.GetPricesDetailsRequest;
import com.klagan.inditex.adapters.exception.InvalidRequestException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ControllerValidator {

    public GetPricesDetailsRequest validate(GetPricesDetailsRequest getPricesDetailsRequest) {
        if (Objects.isNull(getPricesDetailsRequest.getDate()) ||
            Objects.isNull(getPricesDetailsRequest.getProductId()) ||
            Objects.isNull(getPricesDetailsRequest.getBrandId())) {
            throw new InvalidRequestException("productId", "brandId", "date");
        } else {
            return getPricesDetailsRequest;
        }
    }
}
