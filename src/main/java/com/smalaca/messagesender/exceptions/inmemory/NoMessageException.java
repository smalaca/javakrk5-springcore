package com.smalaca.messagesender.exceptions.inmemory;

public class NoMessageException extends RuntimeException {

    private final String errorName;

    public NoMessageException(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorName() {
        return errorName;
    }
}
