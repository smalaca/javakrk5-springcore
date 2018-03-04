package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MailSender {


    public Response sendEmailSender(Message message){

        return Response.aSuccessfulResponseWith("OK");
    }

}
