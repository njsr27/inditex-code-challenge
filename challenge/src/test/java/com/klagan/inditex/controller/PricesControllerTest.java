package com.klagan.inditex.controller;

import com.klagan.inditex.controller.request.GetPricesDetailsRequest;
import com.klagan.inditex.controller.response.GetPricesDetailsResponse;
import com.klagan.inditex.exception.InvalidRequestException;
import com.klagan.inditex.exception.PricesNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PricesControllerTest {

    @Autowired
    private PricesController pricesController;

    @Test
    void contextLoads() {
        Assert.notNull(pricesController, "Main instance must not be null");
    }

    @Test
    void getPricesDetailsOkTest1() {
        ResponseEntity<GetPricesDetailsResponse> responseEntity = pricesController.getPricesDetails(
            GetPricesDetailsRequest.builder()
                .date("2020-06-14-10.00.00")
                .productId(35455)
                .brandId(1)
                .build()
        );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(35455, responseEntity.getBody().getProductId());
        assertEquals(1, responseEntity.getBody().getBrandId());
        assertEquals(1, responseEntity.getBody().getPriceId());
        assertEquals(0, responseEntity.getBody().getPriceToApply().compareTo(new BigDecimal("35.50")));
        assertEquals("2020-06-14-00.00.00", responseEntity.getBody().getStartDate());
        assertEquals("2020-12-31-23.59.59", responseEntity.getBody().getEndDate());
    }

    @Test
    void getPricesDetailsOkTest2() {
        ResponseEntity<GetPricesDetailsResponse> responseEntity = pricesController.getPricesDetails(
            GetPricesDetailsRequest.builder()
                .date("2020-06-14-16.00.00")
                .productId(35455)
                .brandId(1)
                .build()
        );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(35455, responseEntity.getBody().getProductId());
        assertEquals(1, responseEntity.getBody().getBrandId());
        assertEquals(2, responseEntity.getBody().getPriceId());
        assertEquals(0, responseEntity.getBody().getPriceToApply().compareTo(new BigDecimal("25.45")));
        assertEquals("2020-06-14-15.00.00", responseEntity.getBody().getStartDate());
        assertEquals("2020-06-14-18.30.00", responseEntity.getBody().getEndDate());
    }

    @Test
    void getPricesDetailsOkTest3() {
        ResponseEntity<GetPricesDetailsResponse> responseEntity = pricesController.getPricesDetails(
            GetPricesDetailsRequest.builder()
                .date("2020-06-14-21.00.00")
                .productId(35455)
                .brandId(1)
                .build()
        );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(35455, responseEntity.getBody().getProductId());
        assertEquals(1, responseEntity.getBody().getBrandId());
        assertEquals(1, responseEntity.getBody().getPriceId());
        assertEquals(0, responseEntity.getBody().getPriceToApply().compareTo(new BigDecimal("35.50")));
        assertEquals("2020-06-14-00.00.00", responseEntity.getBody().getStartDate());
        assertEquals("2020-12-31-23.59.59", responseEntity.getBody().getEndDate());
    }

    @Test
    void getPricesDetailsOkTest4() {
        ResponseEntity<GetPricesDetailsResponse> responseEntity = pricesController.getPricesDetails(
            GetPricesDetailsRequest.builder()
                .date("2020-06-15-10.00.00")
                .productId(35455)
                .brandId(1)
                .build()
        );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(35455, responseEntity.getBody().getProductId());
        assertEquals(1, responseEntity.getBody().getBrandId());
        assertEquals(3, responseEntity.getBody().getPriceId());
        assertEquals(0, responseEntity.getBody().getPriceToApply().compareTo(new BigDecimal("30.50")));
        assertEquals("2020-06-15-00.00.00", responseEntity.getBody().getStartDate());
        assertEquals("2020-06-15-11.00.00", responseEntity.getBody().getEndDate());
    }

    @Test
    void getPricesDetailsOkTest5() {
        ResponseEntity<GetPricesDetailsResponse> responseEntity = pricesController.getPricesDetails(
            GetPricesDetailsRequest.builder()
                .date("2020-06-16-21.00.00")
                .productId(35455)
                .brandId(1)
                .build()
        );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(35455, responseEntity.getBody().getProductId());
        assertEquals(1, responseEntity.getBody().getBrandId());
        assertEquals(4, responseEntity.getBody().getPriceId());
        assertEquals(0, responseEntity.getBody().getPriceToApply().compareTo(new BigDecimal("38.95")));
        assertEquals("2020-06-15-16.00.00", responseEntity.getBody().getStartDate());
        assertEquals("2020-12-31-23.59.59", responseEntity.getBody().getEndDate());
    }

    @Test
    void testErrorPricesNotFound() {
        try {
            pricesController.getPricesDetails(
                GetPricesDetailsRequest.builder()
                    .date("2020-06-16-21.00.00")
                    .productId(35455)
                    .brandId(5)
                    .build()
            );
        } catch (PricesNotFoundException e) {
            assertNotNull(e);
            assertEquals("Prices with productId 35455 and brandId 5 were not found.", e.getMessage());
        }
    }

    @Test
    void testErrorInvalidDateFormat() {
        try {
            pricesController.getPricesDetails(
                GetPricesDetailsRequest.builder()
                    .date("2020-06-16 21:00:00")
                    .productId(35455)
                    .brandId(5)
                    .build()
            );
        } catch (DateTimeParseException e) {
            assertNotNull(e);
        }
    }

    @Test
    void testErrorNullQueryParams() {
        try {
            pricesController.getPricesDetails(
                GetPricesDetailsRequest.builder()
                    .date("2020-06-16 21:00:00")
                    .productId(35455)
                    .brandId(null)
                    .build()
            );
        } catch (InvalidRequestException e) {
            assertNotNull(e);
            assertEquals("Request requires all these parameters without null values: productId brandId date", e.getMessage());
        }
    }

}
