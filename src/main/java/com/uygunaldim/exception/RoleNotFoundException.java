package com.uygunaldim.exception;

import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends UygunAldimException {
    private static final String ERROR_CODE = "UYGNALDM-ROLE-404";
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;
    public RoleNotFoundException(String errorCode, String errorMessage, HttpStatus httpStatus) {
        super(ERROR_CODE, errorMessage, HTTP_STATUS);
    }
}
