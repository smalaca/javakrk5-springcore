package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import org.springframework.stereotype.Component;

@Component
public class FakeMailSender implements MailSender {

    @Override
    public Response sendEmailSender(Message message) {
        return Response.aSuccessfulResponseWith("OK");
    }

    @Override
    public Response isSuccessfullSended() {
        return Response.aSuccessfulResponseWith("Success");
    }

    @Override
    public Response isNotSuccessFullSended() {
        return Response.aFailureResponse("Error");
    }
}
