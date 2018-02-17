package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageRepository;

public class MessageCrud {
    private final MessageRepository messageRepository;

    public MessageCrud(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Response createNew(
            String subject, String body, String from, String to) {
        Message message = new Message.MessageBuilder()
                .withBody(body)
                .withSubject(subject)
                .withFrom(from)
                .withTo(to)
                .build();

        if (!messageRepository.exists(message)) {
            message.setId("1");
            messageRepository.add(message);

            return Response.aSuccessfulResponseWith("1");
        }

        return Response.aFailureResponse("Message already exists");
    }
}
