package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageRepository;

public class MessageCrud {
    private final MessageRepository messageRepository;

    public MessageCrud(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Response createNew(MessageDto messageDto) {
        Message message = new Message.MessageBuilder()
                .withBody(messageDto.getBody())
                .withSubject(messageDto.getSubject())
                .withFrom(messageDto.getFrom())
                .withTo(messageDto.getTo())
                .build();


        if (!messageRepository.exists(message)) {
            message.setId("1");
            messageRepository.add(message);

            return Response.aSuccessfulResponseWith("1");
        }

        return Response.aFailureResponse("Message already exists");
    }
}
