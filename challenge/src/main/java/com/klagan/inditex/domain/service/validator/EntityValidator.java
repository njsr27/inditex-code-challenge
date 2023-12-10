package com.klagan.inditex.domain.service.validator;

import com.klagan.inditex.adapters.exception.PricesNotFoundException;
import com.klagan.inditex.domain.repository.entity.PricesEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EntityValidator {

    public PricesEntity validate(PricesEntity entity, Integer productId, Integer brandId) {
        if (Objects.isNull(entity)) {
            throw new PricesNotFoundException(productId, brandId);
        } else {
            return entity;
        }
    }
}
