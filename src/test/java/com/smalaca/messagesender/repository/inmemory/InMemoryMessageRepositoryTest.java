package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.exceptions.inmemory.MessageDoesNotExistException;
import com.smalaca.messagesender.service.MessageDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/repositories.xml"})
public class InMemoryMessageRepositoryTest {

    private static final String SOME_SUBJECT = "some subject";
    private static final String SOME_BODY = "some body";
    public static final String TO_SOMEONE = "javakrk5";
    public static final String FROM_SOMEONE = "smalaca";

    @Autowired private InMemoryMessageRepository repository;
    private MessageFactory factory = new MessageFactory();

    private Message setMessageAndAddToRepository() {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject(SOME_SUBJECT);
        messageDto.setBody(SOME_BODY);
        messageDto.setTo(TO_SOMEONE);
        messageDto.setFrom(FROM_SOMEONE);

        Message message = factory.createFrom(messageDto, UUID.randomUUID().toString());
        repository.add(message);
        return message;
    }

    @Test
    public void shouldRecognizeThatMessageExist() {

        Message message = setMessageAndAddToRepository();

        assertTrue(repository.exists(message));
    }

    @Test
    public void shouldDeletePreviouslyCreatedNewMessage(){

        Message message = setMessageAndAddToRepository();
        String messageId = message.getId();

        repository.delete(messageId);

        Assert.assertFalse(repository.exists(messageId));
    }

    @Test(expected = MessageDoesNotExistException.class)
    public void shouldReturnMessageDoesNotExistException() {
        repository.delete("1234");
    }

    @Test
    public void shouldReturnListOfMessagesThatIsNotEmpty() {

        setMessageAndAddToRepository();
        Assert.assertFalse(repository.getMessages().isEmpty());
    }

    @Test
    public void shouldReturnListOfMessagesWithNoElement() {

        Assert.assertTrue(repository.getMessages().isEmpty());
    }
}