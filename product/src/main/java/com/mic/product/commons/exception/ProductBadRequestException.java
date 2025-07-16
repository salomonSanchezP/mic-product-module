package com.mic.product.commons.exception;

public class ProductBadRequestException extends RuntimeException {
    public ProductBadRequestException(String message) {
        super(message);
    }
}
