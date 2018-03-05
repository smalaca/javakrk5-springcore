package com.smalaca.messagesender.exceptions.inmemory;

public class MessageSendException extends RuntimeException{
    private final String ex = "Can't send message";

    public MessageSendException() {
    }
}
