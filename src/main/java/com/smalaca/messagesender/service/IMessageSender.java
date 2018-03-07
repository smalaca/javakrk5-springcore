package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;

public interface IMessageSender {

    Response sendMessage(Message message);
    boolean isSuccessfullSended();
}
