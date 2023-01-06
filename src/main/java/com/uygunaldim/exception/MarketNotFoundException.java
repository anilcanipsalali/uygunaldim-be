package com.uygunaldim.exception;

import org.springframework.http.HttpStatus;

public class MarketNotFoundException extends UygunAldimException {

    private static final String ERROR_CODE = "UYGNALDM-MARKET-404";
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;
    public MarketNotFoundException(String errorCode, String errorMessage, HttpStatus httpStatus) {
        super(ERROR_CODE, errorMessage, HTTP_STATUS);
    }
}
