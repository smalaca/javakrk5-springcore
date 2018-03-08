package com.smalaca.messagesender.domain;

import com.smalaca.messagesender.service.MessageDto;

import java.util.List;

public interface MessageRepository {

    boolean exists(String id);

    void add(Message message);

    boolean exists(Message message);

    void delete(String messageId);

    List<Message> getAllMessages();

    void update(String id, MessageDto messageDto);
}
