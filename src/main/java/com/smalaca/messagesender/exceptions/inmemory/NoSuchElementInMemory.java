package com.smalaca.messagesender.exceptions.inmemory;

public class NoSuchElementInMemory extends RuntimeException {

    private final String message = "NOT EXISTS";

    private int id;

    public NoSuchElementInMemory() {
    }

    @Override
    public String getMessage() {
        return id + " " + message;
    }
}
