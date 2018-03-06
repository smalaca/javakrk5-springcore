package com.smalaca.messagesender.exceptions.inmemory;

import com.smalaca.messagesender.repository.inmemory.InMemoryMessageRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageDoesNotExistExceptionTest {

    @Autowired
    InMemoryMessageRepository messageRepository = new InMemoryMessageRepository();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldTestExceptionMessage() throws MessageDoesNotExistException {

        thrown.expect(MessageDoesNotExistException.class);
        thrown.expectMessage("Message with id: 102 does not exist.");
        messageRepository.delete("102");
    }
}