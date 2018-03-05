package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import org.springframework.stereotype.Component;

@Component
public class MailSender {


    public Response sendEmailSender(Message message){

        return Response.aSuccessfulResponseWith("OK");
    }

}
