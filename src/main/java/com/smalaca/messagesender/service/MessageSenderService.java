package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageRepository;
import com.smalaca.messagesender.exceptions.NoMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {
    private final MessageRepository messageRepository;
    private final FakeMailSender emailSenderFake;

    @Autowired
    public MessageSenderService(MessageRepository messageRepository, FakeMailSender emailSenderFake) {
        this.messageRepository = messageRepository;
        this.emailSenderFake = emailSenderFake;
    }


    public Response sendMessageViaEmail(String messageId) throws Exception {

        try {
            if (messageRepository.exists(messageId)) {
                Message message = messageRepository.getMessageById(messageId);
                emailSenderFake.sendMessage(message);
                Response.aSuccessfulResponseWith("Mail was send");
            }
        } catch (NoMessageException e) {
            throw new NoMessageException("Error, mail is stoped.");
        }
        return Response.aSuccessfulResponse();
    }
}
