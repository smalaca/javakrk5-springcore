package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.domain.MessageRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageCrud {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageCrud(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Response createNew(MessageDto messageDto) {
        Message message = new MessageFactory().createFrom(messageDto);

        if (!messageRepository.exists(message)) {
            message.setId(UUID.randomUUID().toString());
            messageRepository.add(message);

            return Response.aSuccessfulResponseWith("1");
        }

        return Response.aFailureResponse("Message already exists");
    }

    public Response deleteMessage(String messageId) {
        if (messageRepository.exists(messageId)) {
            messageRepository.delete(messageId);
            return Response.aSuccessfulResponse();
        } else {
            return Response.aFailureResponse("Message with requested id does no exists!");
        }
    }

    public List<Message> getAllMessages() {
        return messageRepository.getMessages();
    }
}
