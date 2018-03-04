package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.service.MessageCrud;
import com.smalaca.messagesender.service.MessageDto;
import com.smalaca.messagesender.service.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/repositories.xml", "/fake-messages.xml"})
public class InMemoryMessageRepositoryTest {
    private static final String SOME_SUBJECT = "some subject";
    private static final String SOME_BODY = "some body";

    @Autowired
    private MessageRepository repository;

    private MessageFactory factory = new MessageFactory();

    @Test
    public void shouldRecognizeThatFirstMessageExist() {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject(SOME_SUBJECT);
        messageDto.setBody(SOME_BODY);

        assertTrue(repository.exists(factory.createFrom(messageDto)));
    }

    @Test
    public void shouldRecognizeThatMessageSimilarToFirstDoesNotExist() {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject("some subject");
        messageDto.setBody("some body");

        assertFalse(repository.exists(factory.createFrom(messageDto, "some id")));
    }

    @Test
    public void shouldRecognizeThatSecodeMessageExist() {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject(SOME_SUBJECT);
        messageDto.setBody(SOME_BODY);

        assertTrue(repository.exists(factory.createFrom(messageDto, "123456")));
    }

    @Test
    public void shouldRecognizeThatMessageSimilarToSecondDoesNotExist() {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject("some");
        messageDto.setBody("some");

        assertFalse(repository.exists(factory.createFrom(messageDto, "1234567")));
    }

    @Test
    public void shouldRecognizeThatThirdMessageExist() {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject(SOME_SUBJECT);
        messageDto.setBody(SOME_BODY);
        messageDto.setTo("javakrk5");
        messageDto.setFrom("smalaca");

        assertTrue(repository.exists(factory.createFrom(messageDto, "123456")));
    }

    @Test
    public void shouldRecognizeThatFourthMessageExist() {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject("hot topic");
        messageDto.setBody("nice body");

        assertTrue(repository.exists(factory.createFrom(messageDto)));
    }

    @Test
    public void shouldRecognizeThatMessageSimilarToThirdDoesNotExist() {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject("some subject 2");
        messageDto.setBody("some body 2");
        messageDto.setTo("smalaca");
        messageDto.setFrom("javakrk5");

        assertFalse(repository.exists(factory.createFrom(messageDto, "123456")));
    }

    @Test
    public void shouldGetMessageByID() {
        MessageDto messageDto = new MessageDto();
        repository.add(new MessageFactory().createFrom(messageDto, "1"));
        Message message = repository.getMessageById("1");

        Assert.assertNotNull(repository.getMessageById("1"));


    }
}