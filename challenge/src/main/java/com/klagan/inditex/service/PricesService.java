package com.klagan.inditex.service;

import com.klagan.inditex.model.Prices;
import com.klagan.inditex.persistence.entity.PricesEntity;
import com.klagan.inditex.persistence.repository.PricesRepository;
import com.klagan.inditex.service.formatter.DateFormatter;
import com.klagan.inditex.service.mapper.PricesEntityToPricesMapper;
import com.klagan.inditex.service.validator.EntityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PricesService {

    private final PricesRepository pricesRepository;
    private final PricesEntityToPricesMapper pricesMapper;
    private final EntityValidator validator;
    private final DateFormatter formatter;

    public Prices getPricesDetails(String date, Integer productId, Integer brandId) {
        LocalDateTime formattedDate = formatter.format(date);
        PricesEntity pricesDetails = pricesRepository.getPricesDetails(formattedDate, productId, brandId);
        PricesEntity validate = validator.validate(pricesDetails, productId, brandId);
        Prices map = pricesMapper.map(validate);
        return map;
    }

}
