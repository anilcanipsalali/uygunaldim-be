package com.uygunaldim.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends UygunAldimException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;
    public AlreadyExistsException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage, HTTP_STATUS);
    }
}
