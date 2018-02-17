package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/repositories.xml")
public class InMemoryMessageRepositoryTest {
    @Autowired private InMemoryMessageRepository repository;

    @Test
    public void shouldRecognizeThatFirstMessageExist() {
        Message message = new Message("some subject", "some body");

        assertTrue(repository.exists(message));
    }

    @Test
    public void shouldRecognizeThatMessageSimilarToFirstDoeasExist() {
        Message message = new Message("some subject", "some body");
        message.setId("some id");

        assertFalse(repository.exists(message));
    }

    @Test
    public void shouldRecognizeThatSecodeMessageExist() {
        Message message = new Message("some", "some");
        message.setId("123456");

        assertTrue(repository.exists(message));
    }

    @Test
    public void shouldRecognizeThatMessageSimilarToSecondDoeasExist() {
        Message message = new Message("some", "some");
        message.setId("1234567");

        assertFalse(repository.exists(message));
    }

    @Test
    public void shouldRecognizeThatThirdMessageExist() {
        Message message = new Message("some subject 2", "some body 2");
        message.setId("123456");
        message.setTo("javakrk5");
        message.setFrom("smalaca");

        assertTrue(repository.exists(message));
    }

    @Test
    public void shouldRecognizeThatMessageSimilarToThirdDoeasExist() {
        Message message = new Message("some subject 2", "some body 2");
        message.setId("123456");
        message.setTo("smalaca");
        message.setFrom("javakrk5");

        assertFalse(repository.exists(message));
    }

    @Test
    public void shouldRecognizeThatFourthMessageExist() {
        Message message = new Message("another", "message");
        message.setFrom("smalaca");

        assertTrue(repository.exists(message));
    }
}