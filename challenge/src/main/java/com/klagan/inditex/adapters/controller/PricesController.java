package com.klagan.inditex.adapters.controller;

import com.klagan.inditex.adapters.controller.request.GetPricesDetailsRequest;
import com.klagan.inditex.adapters.controller.response.GetPricesDetailsResponse;
import com.klagan.inditex.domain.service.PricesService;
import com.klagan.inditex.domain.service.mapper.PricesToPricesResponseMapper;
import com.klagan.inditex.domain.service.validator.ControllerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PricesController {

    private final PricesService pricesService;
    private final PricesToPricesResponseMapper mapper;
    private final ControllerValidator validator;

    @GetMapping("/prices")
    public ResponseEntity<GetPricesDetailsResponse> getPricesDetails(GetPricesDetailsRequest getPricesDetailsRequest) {
        GetPricesDetailsRequest validatedRequest = validator.validate(getPricesDetailsRequest);
        return ResponseEntity.ok()
            .body(
                mapper.map(
                    pricesService.getPricesDetails(
                        validatedRequest.getDate(),
                        validatedRequest.getProductId(),
                        validatedRequest.getBrandId()
                    )
                )
            );
    }

}
