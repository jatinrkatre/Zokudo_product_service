package com.cards.zokudo.exceptions;

import org.springframework.http.HttpStatus;

public class CustomRuntimeException extends RuntimeException {

    private boolean valid = false;
    private String message;
    private HttpStatus httpStatus;

    public CustomRuntimeException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public CustomRuntimeException(boolean valid, String message, HttpStatus httpStatus) {
        super(message);
        this.valid = valid;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
