package com.smalaca.messagesender.rest.api;

public class MessageWithGivenIdDoesNotExistException extends RuntimeException {
    private final String id;

    public MessageWithGivenIdDoesNotExistException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
