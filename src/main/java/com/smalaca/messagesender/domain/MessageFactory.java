package com.smalaca.messagesender.domain;

import com.smalaca.messagesender.service.MessageDto;

public class MessageFactory {
    public Message createFrom(MessageDto messageDto) {
        return new Message.MessageBuilder()
                .withBody(messageDto.getBody())
                .withSubject(messageDto.getSubject())
                .withFrom(messageDto.getFrom())
                .withTo(messageDto.getTo())
                .build();
    }
}
