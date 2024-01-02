package com.ugurukku.linkshortener.exception;

public class AuthenticationError extends RuntimeException {
    public AuthenticationError() {
    }

    public AuthenticationError(String message) {
        super(message);
    }
}
