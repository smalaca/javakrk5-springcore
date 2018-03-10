package com.smalaca.messagesender.sender.slack.fake;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.sender.MessageSender;
import com.smalaca.messagesender.service.Response;

public class FakeSlackSender implements MessageSender {

    @Override
    public Response sendMessage(Message message) {
        return Response.aSuccessfulResponseWith("OK");
    }
}
