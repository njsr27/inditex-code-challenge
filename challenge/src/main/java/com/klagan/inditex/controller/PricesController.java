package com.klagan.inditex.controller;

import com.klagan.inditex.controller.request.GetPricesDetailsRequest;
import com.klagan.inditex.controller.response.GetPricesDetailsResponse;
import com.klagan.inditex.service.PricesService;
import com.klagan.inditex.service.mapper.PricesToPricesResponseMapper;
import com.klagan.inditex.service.validator.ControllerValidator;
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
    ResponseEntity<GetPricesDetailsResponse> getPricesDetails(GetPricesDetailsRequest getPricesDetailsRequest) {
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
