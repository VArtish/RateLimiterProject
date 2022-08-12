package com.example.demo.exception;

public class RateLimiterException extends Exception {
    public RateLimiterException() {
        super();
    }

    public RateLimiterException(String message) {
        super(message);
    }

    public RateLimiterException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
