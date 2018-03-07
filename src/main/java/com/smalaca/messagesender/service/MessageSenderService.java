package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageRepository;
import com.smalaca.messagesender.exceptions.NoMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MessageSenderService {
    private final MessageRepository messageRepository;
    private final FakeMailSender emailSenderFake;

    @Autowired
    public MessageSenderService(MessageRepository messageRepository, FakeMailSender emailSenderFake) {
        this.messageRepository = messageRepository;
        this.emailSenderFake = emailSenderFake;
    }


    public Response sendMessageViaEmail(String messageId) {

        try {
            Message message = messageRepository.getMessageById(messageId);
            return emailSenderFake.sendMessage(message);
        } catch (NoSuchElementException e) {
            throw new NoMessageException(messageId);
        }
    }

    public Response sendMessageViaEmialWasSuccess() {
        return Response.aSuccessfulResponse();

    }

}
