package com.smalaca.messagesender.exceptions;

public class NoMessageException extends RuntimeException {
    private final String messageId;

    public NoMessageException(String messageId) {
        super("Message " + messageId + " doesn't exist");
        this.messageId = messageId;
    }

    public String getMessageId() {
        return messageId;
    }

}
