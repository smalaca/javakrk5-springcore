package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryMessageRepository implements MessageRepository {
    private List<Message> messages;

    public InMemoryMessageRepository() {
        this(new ArrayList<>());
    }

    public InMemoryMessageRepository(List<Message> messages) {
        System.out.println("Creation of the InMemoryMessageRepository");
        this.messages = messages;
    }

    public void runBeforeUsage() {
        System.out.println("init method");
    }

    public void runBeforeDie() {
        System.out.println("I'm going to die");
    }

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
