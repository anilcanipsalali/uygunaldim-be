package com.uygunaldim.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.uygunaldim.util.ApplicationConstants.*;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception e) {
        log.error(UYGUNALDIM_EXCEPTION_LOG, e.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(RETURN_CODE, INTERNAL_SERVER_ERROR_CODE);
        responseHeaders.add(RETURN_MESSAGE, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(UYGUNALDIM_EXCEPTION_LOG, e.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(RETURN_CODE, BAD_REQUEST_CODE);
        responseHeaders.add(RETURN_MESSAGE, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(responseHeaders).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleParameterNotFoundException(UygunAldimException e) {
        log.error(UYGUNALDIM_EXCEPTION_LOG, e.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(RETURN_CODE, e.getErrorCode());
        responseHeaders.add(RETURN_MESSAGE, e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(responseHeaders).build();
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExistsException(UygunAldimException e) {
        log.error(UYGUNALDIM_EXCEPTION_LOG, e.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(RETURN_CODE, e.getErrorCode());
        responseHeaders.add(RETURN_MESSAGE, e.getErrorMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(responseHeaders).build();
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAlreadyExistsException(AuthenticationException e) {
        log.error(UYGUNALDIM_EXCEPTION_LOG, e.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(RETURN_CODE, BAD_REQUEST_CODE);
        responseHeaders.add(RETURN_MESSAGE, e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(responseHeaders).build();
    }
}
