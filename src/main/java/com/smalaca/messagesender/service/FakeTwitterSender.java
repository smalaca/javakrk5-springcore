package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;

public class FakeTwitterSender implements IMessageSender {

    @Override
    public Response sendMessage(Message message) {
        return Response.aSuccessfulResponseWith("OK");
    }
}
