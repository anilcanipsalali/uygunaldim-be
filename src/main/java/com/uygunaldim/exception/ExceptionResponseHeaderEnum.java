package com.uygunaldim.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionResponseHeaderEnum {
    RETURN_CODE("uygunaldim-return-code"), RETURN_MESSAGE("uygunaldim-return-message");
    private String header;
    public String headerName() {
        return header;
    }
}
