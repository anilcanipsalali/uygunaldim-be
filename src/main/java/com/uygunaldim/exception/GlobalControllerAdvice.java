package com.uygunaldim.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.uygunaldim.exception.ExceptionResponseHeaderEnum.RETURN_CODE;
import static com.uygunaldim.exception.ExceptionResponseHeaderEnum.RETURN_MESSAGE;
@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception e) {
        log.error("Exception: {}", e.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(RETURN_CODE.headerName(), "UYGNALDM-00500");
        responseHeaders.add(RETURN_MESSAGE.headerName(), e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: {}", e.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(RETURN_CODE.headerName(), "UYGNALDM-00400");
        responseHeaders.add(RETURN_MESSAGE.headerName(), e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).build();
    }

    @ExceptionHandler(value = {
            MarketNotFoundException.class,
            PermissionNotFoundException.class,
            RoleNotFoundException.class,
            ProductNotFoundException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<String> handleParameterNotFoundException(UygunAldimException e) {
        log.error("UygunAldimException: {}", e.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(RETURN_CODE.headerName(), e.getErrorCode());
        responseHeaders.add(RETURN_MESSAGE.headerName(), e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).build();
    }
}
