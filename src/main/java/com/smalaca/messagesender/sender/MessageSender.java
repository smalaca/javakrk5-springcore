package com.smalaca.messagesender.sender;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.service.Response;

public interface MessageSender {

    Response sendMessage(Message message);
}
