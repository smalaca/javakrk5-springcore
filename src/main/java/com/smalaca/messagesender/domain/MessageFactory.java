package com.smalaca.messagesender.domain;

import com.smalaca.messagesender.service.MessageDto;

public class MessageFactory {
    public Message createFrom(MessageDto messageDto, String id) {
        return aMessageBuilder(messageDto)
                .withId(id)
                .build();
    }

    public Message createFrom(MessageDto messageDto) {
        return aMessageBuilder(messageDto).build();
    }

    private Message.MessageBuilder aMessageBuilder(MessageDto messageDto) {
        return new Message.MessageBuilder()
                .withBody(messageDto.getBody())
                .withSubject(messageDto.getSubject())
                .withFrom(messageDto.getFrom())
                .withTo(messageDto.getTo());
    }
}
