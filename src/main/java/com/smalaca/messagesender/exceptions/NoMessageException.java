package com.smalaca.messagesender.exceptions;

public class NoMessageException extends RuntimeException {
    private final String messageId;

    public NoMessageException(String messageId) {
        super(messageId);
        this.messageId = messageId;
    }
}
