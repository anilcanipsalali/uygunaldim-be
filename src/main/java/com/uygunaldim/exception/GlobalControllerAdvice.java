package com.uygunaldim.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.uygunaldim.util.ApplicationConstants.RETURN_CODE;
import static com.uygunaldim.util.ApplicationConstants.RETURN_MESSAGE;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception e) {
        log.error("Exception: {}", e.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(RETURN_CODE, "UYGNALDM-00500");
        responseHeaders.add(RETURN_MESSAGE, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: {}", e.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(RETURN_CODE, "UYGNALDM-00400");
        responseHeaders.add(RETURN_MESSAGE, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleParameterNotFoundException(UygunAldimException e) {
        log.error("UygunAldimException: {}", e.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(RETURN_CODE, e.getErrorCode());
        responseHeaders.add(RETURN_MESSAGE, e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(responseHeaders).build();
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExistsException(UygunAldimException e) {
        log.error("UygunAldimException: {}", e.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(RETURN_CODE, e.getErrorCode());
        responseHeaders.add(RETURN_MESSAGE, e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(responseHeaders).build();
    }
}
