package com.smalaca.messagesender.domain;

import com.smalaca.messagesender.service.MessageDto;

public class MessageFactory {
    private static final String NO_ID = "";

    public Message createFrom(MessageDto messageDto) {
        return createFrom(messageDto, NO_ID);
    }

    public Message createFrom(MessageDto messageDto, String id) {
        return aMessageBuilder(messageDto)
                .withId(id)
                .build();
    }

    private Message.MessageBuilder aMessageBuilder(MessageDto messageDto) {
        return new Message.MessageBuilder()
                .withBody(messageDto.getBody())
                .withSubject(messageDto.getSubject())
                .withFrom(messageDto.getFrom())
                .withTo(messageDto.getTo());
    }
}
