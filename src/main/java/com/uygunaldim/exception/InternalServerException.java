package com.uygunaldim.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends UygunAldimException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
    public InternalServerException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage, HTTP_STATUS);
    }
}
