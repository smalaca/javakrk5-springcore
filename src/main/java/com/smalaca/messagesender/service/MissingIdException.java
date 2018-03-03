package com.smalaca.messagesender.service;

public class MissingIdException extends RuntimeException {

    public MissingIdException(String message) {
        super(message);
    }
}
