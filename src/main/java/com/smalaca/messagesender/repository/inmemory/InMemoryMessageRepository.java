package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class InMemoryMessageRepository implements MessageRepository {
    @Autowired(required = false) private List<Message> messages;

    public boolean exists(String id) {
        return aMessages()
                .stream()
                .anyMatch(message -> message.hasSameId(id));
    }

    public void add(Message message) {
        aMessages().add(message);
    }

    public boolean exists(Message message) {
        return aMessages().contains(message);
    }

    private List<Message> aMessages() {
        if (messages == null) {
            messages = new ArrayList<>();
        }

        return messages;
    }
}
