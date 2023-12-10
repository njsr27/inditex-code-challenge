package com.klagan.inditex.adapters.exception;

public class PricesNotFoundException extends RuntimeException {

    public PricesNotFoundException(Integer productId, Integer brandId) {
        super("Prices with productId " + productId + " and brandId " + brandId + " were not found.");
    }
}
