package com.smalaca.messagesender.domain;

public class MessageFactory {
    public Message aMessage(String subject, String body) {
        return new Message.MessageBuilder()
                .withSubject(subject)
                .withBody(body)
                .build();
    }
}
