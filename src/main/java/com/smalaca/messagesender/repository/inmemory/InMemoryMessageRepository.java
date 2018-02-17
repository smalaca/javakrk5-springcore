package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.MessageRepository;

public class InMemoryMessageRepository implements MessageRepository {
    private boolean addInvoked;

    public boolean exists(String id) {
        return addInvoked;
    }

    public void add() {
        addInvoked = true;
    }
}
