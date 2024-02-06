package com.testemilio.pricelistservice.domain.exception;

public class PriceException extends RuntimeException {
    public PriceException(String message, Throwable cause) {
        super(message, cause);
    }
    public PriceException(String message) {
        super(message);
    }

}
