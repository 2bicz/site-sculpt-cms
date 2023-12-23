package com.github.bicz.sitesculpt.exception;

public class RequestNotCorrectException extends RuntimeException {
    public RequestNotCorrectException() {
        super();
    }

    public RequestNotCorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestNotCorrectException(String message) {
        super(message);
    }

    public RequestNotCorrectException(Throwable cause) {
        super(cause);
    }
}
