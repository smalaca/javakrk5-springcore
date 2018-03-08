package com.smalaca.messagesender.exceptions;

public class NoMessageException extends RuntimeException {
    private String errorMessage;

    public NoMessageException() {
    }

    public NoMessageException(String message) {
        this.errorMessage = message;
    }

    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
