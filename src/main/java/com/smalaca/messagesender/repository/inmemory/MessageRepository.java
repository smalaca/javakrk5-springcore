package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.Message;

public interface MessageRepository {

    boolean exists(String id);

    void add(Message message);

    boolean exists(Message message);

    Message getMessageById(String id);

}
