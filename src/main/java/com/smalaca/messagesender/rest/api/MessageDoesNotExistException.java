package com.smalaca.messagesender.rest.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MessageDoesNotExistException extends RuntimeException {
    private final String id;

    public MessageDoesNotExistException(String id) {
        super("Message with id: " + id + " does not exist.");
        this.id = id;
    }
}
