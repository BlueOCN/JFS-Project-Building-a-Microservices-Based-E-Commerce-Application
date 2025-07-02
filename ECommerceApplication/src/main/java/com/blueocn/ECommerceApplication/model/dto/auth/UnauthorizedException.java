package com.blueocn.ECommerceApplication.model.dto.auth;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
