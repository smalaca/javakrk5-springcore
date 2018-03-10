package com.smalaca.messagesender.exceptions.inmemory;

public class NoMessageException extends RuntimeException {

    private String errorName;

    public NoMessageException() {
    }

    public NoMessageException(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }
}
