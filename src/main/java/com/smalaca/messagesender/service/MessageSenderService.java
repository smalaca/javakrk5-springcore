package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageRepository;
import com.smalaca.messagesender.exceptions.inmemory.NoMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {

    private MessageRepository messageRepository;
    private FakeMailSender fakeMailSender;

    @Autowired
    public MessageSenderService(MessageRepository messageRepository, FakeMailSender fakeMailSender) {
        this.messageRepository = messageRepository;
        this.fakeMailSender = fakeMailSender;
    }

    public Response sendMessageViaEmail(String id) throws NoMessageException {

        try {
            if (messageRepository.exists(id)) {

                Message message = messageRepository.getMessagesById(id);
                fakeMailSender.sendMessage(message);
                return Response.aSuccessfulResponseWith("Mail " + id + " was send");
            }
        } catch (NoMessageException ex) {
            throw new NoMessageException("Error, cannot send " + id + " mail");
        }
        return Response.aSuccessfulResponse();
    }
}
