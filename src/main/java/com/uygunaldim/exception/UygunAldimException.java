package com.uygunaldim.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public abstract class UygunAldimException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
}
