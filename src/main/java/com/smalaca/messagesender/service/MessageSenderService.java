package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.User;
import com.smalaca.messagesender.repository.inmemory.MessageRepository;
import com.smalaca.messagesender.repository.inmemory.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService{


    @Autowired
    public MessageRepository messageRepository;

    @Autowired
    public MailSender emailSender;


    public Response sendMessageViaEmail(String messageId){

        Message message = messageRepository.getMessageById(messageId);

        Response response = emailSender.sendEmailSender(message);

        return response;
    }



}
