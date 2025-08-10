package com.trainings.virtual_assistant.login.exception;

public class JwtSigningException extends RuntimeException {
    public JwtSigningException(String message) {
        super(message);
    }
}
