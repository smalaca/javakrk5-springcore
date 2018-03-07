package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import org.springframework.stereotype.Component;


@Component
public class FakeTwitterSender implements IMessageSender {
    @Override
    public Response sendMessage(Message message) {
        return Response.aSuccessfulResponseWith("OK");
    }

    @Override
    public boolean isSuccessfullSended() {
        return Response.aSuccessfulResponse().isSuccess();
    }
}
