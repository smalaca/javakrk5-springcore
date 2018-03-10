package com.smalaca.messagesender.sender.mail.fake;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.sender.MessageSender;
import com.smalaca.messagesender.service.Response;
import org.springframework.stereotype.Component;

@Component
public class FakeMailSender implements MessageSender {

    @Override
    public Response sendMessage(Message message) {
        return Response.aSuccessfulResponseWith("OK");
    }
}
