package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.domain.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            message.setId("1");
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
