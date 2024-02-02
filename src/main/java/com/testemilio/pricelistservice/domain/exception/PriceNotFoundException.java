package com.testemilio.pricelistservice.domain.exception;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
