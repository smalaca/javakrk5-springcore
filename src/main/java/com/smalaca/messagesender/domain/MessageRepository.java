package com.smalaca.messagesender.domain;

import com.smalaca.messagesender.domain.Message;

public interface MessageRepository {

    boolean exists(String id);

    void add(Message message);

    boolean exists(Message message);

    void delete(String messageId);
}
