package com.company.web.springdemo.exceptions;

public class AuthorizationException extends RuntimeException {

    public static final String INVALID_AUTHENTICATION = "Invalid authentication";

    public AuthorizationException() {
        super(INVALID_AUTHENTICATION);
    }
}
