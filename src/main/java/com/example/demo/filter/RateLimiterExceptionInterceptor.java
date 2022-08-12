package com.example.demo.filter;

import com.example.demo.exception.RateLimiterException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RateLimiterExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RateLimiterException.class})
    protected ResponseEntity<Object> handleConflict(RateLimiterException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
