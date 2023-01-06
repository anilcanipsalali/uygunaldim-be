package com.uygunaldim.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends UygunAldimException {
    private static final String ERROR_CODE = "UYGNALDM-PRODUCT-404";
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;
    public ProductNotFoundException(String errorCode, String errorMessage, HttpStatus httpStatus) {
        super(ERROR_CODE, errorMessage, HTTP_STATUS);
    }
}
