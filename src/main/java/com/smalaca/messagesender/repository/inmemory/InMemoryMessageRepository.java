package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageRepository;
import com.smalaca.messagesender.exceptions.inmemory.MessageDoesNotExistException;
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
        this.messages = messages;
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

    @Override
    public void delete(String messageId) {
        int index = -1;
        for (Message message : messages) {
            if (message.hasSameId(messageId)) {
                index = messages.indexOf(message);
            }
        }
        if (index!= -1){
            messages.remove(index);
        } else{
            throw new MessageDoesNotExistException (messageId);
        }
    }

    public List<Message> getMessages() {
        return messages;
    }
}
