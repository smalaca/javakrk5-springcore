package com.smalaca.messagesender.exceptions.inmemory;

public class MessageDoesNotExistException extends RuntimeException {

    public MessageDoesNotExistException(String messageId) {
        super(messageId);
    }

}
