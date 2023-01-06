package com.uygunaldim.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends UygunAldimException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;
    public NotFoundException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage, HTTP_STATUS);
    }
}
