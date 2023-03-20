package com.uygunaldim.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends UygunAldimException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;
    public AuthorizationException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage, HTTP_STATUS);
    }
}
