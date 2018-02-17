package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryMessageRepository implements MessageRepository {
    private final List<Message> messages = new ArrayList<>();

    public boolean exists(String id) {
        return messages
                .stream()
                .anyMatch(message -> message.hasSameId(id));
    }

    public void add(Message message) {
        messages.add(message);
    }

    public boolean exists(Message message) {
        return messages.contains(message);
    }
}
