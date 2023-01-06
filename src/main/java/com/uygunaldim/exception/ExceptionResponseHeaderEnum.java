package com.uygunaldim.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionResponseHeaderEnum {
    RETURN_CODE("uygunaldim-return-code"), RETURN_MESSAGE("uygunaldim-return-message");
    @Getter
    private String header;
}
