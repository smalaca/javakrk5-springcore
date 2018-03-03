package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.exceptions.NoMessageException;
import com.smalaca.messagesender.repository.inmemory.MessageRepository;
import com.smalaca.messagesender.repository.inmemory.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MessageSenderService {


    @Autowired
    public MessageRepository messageRepository;

    @Autowired
    public MailSender emailSender;


    public Response sendMessageViaEmail(String messageId) {

        try {
            Message message = messageRepository.getMessageById(messageId);
            return emailSender.sendEmailSender(message);
        } catch (NoSuchElementException e) {
                throw new NoMessageException(messageId);
        }
    }


}
