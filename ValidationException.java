package com.cards.zokudo.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) { super(message); }

    public ValidationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
