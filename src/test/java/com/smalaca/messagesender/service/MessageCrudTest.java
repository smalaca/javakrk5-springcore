package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.domain.MessageRepository;
import com.smalaca.messagesender.exceptions.inmemory.MessageDoesNotExistException;
import com.smalaca.messagesender.repository.inmemory.InMemoryMessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.any;

public class MessageCrudTest {
    private static final String SUBJECT = "subject";
    private static final String BODY = "body";
    private static final String FROM = "from";
    private static final String TO = "to";

    private MessageDto getMessageDto() {
        MessageDto messageDto = new MessageDto();
        messageDto.setBody(BODY);
        messageDto.setSubject(SUBJECT);
        messageDto.setFrom(FROM);
        messageDto.setTo(TO);
        return messageDto;
    }

    private MessageRepository messageRepository = new InMemoryMessageRepository();
    private MessageCrud messageCrud = new MessageCrud(messageRepository);

    @Test
    public void shouldCreateNewMessage() {

        MessageDto messageDto = getMessageDto();

        Response response = messageCrud.createNew(messageDto);

        Assert.assertTrue(response.isSuccess());
        Assert.assertThat(response.getMessage(), any(String.class));
        Assert.assertTrue(messageRepository.exists(response.getMessage()));
    }

    @Test
    public void shouldNotCreateNewMessage() {

        MessageDto messageDto = getMessageDto();

        messageRepository.add(new MessageFactory().createFrom(messageDto));

        Response response = messageCrud.createNew(messageDto);

        Assert.assertFalse(response.isSuccess());
    }

    @Test
    public void shouldDeletePreviouslyCreatedNewMessage() {
        MessageDto messageDto = getMessageDto();
        Response responseFromCreation = messageCrud.createNew(messageDto);

        Response response = messageCrud.deleteMessage(responseFromCreation.getMessage());

        Assert.assertTrue(response.isSuccess());
        Assert.assertFalse(messageRepository.exists(responseFromCreation.getMessage()));
    }

    @Test
    public void shouldNotDeletePreviouslyCreatedNewMessage() {

        MessageDto messageDto = getMessageDto();
        Response responseFromCreation = messageCrud.createNew(messageDto);

        Response response = messageCrud.deleteMessage(responseFromCreation.getMessage().toUpperCase());

        Assert.assertFalse(response.isSuccess());
        Assert.assertTrue(messageRepository.exists(responseFromCreation.getMessage()));
    }

    @Test
    public void shouldReturnListOfMessagesThatIsNotEmpty() {

        MessageDto messageDto = getMessageDto();
        messageCrud.createNew(messageDto);

        Assert.assertFalse(messageRepository.getMessages().isEmpty());
    }

    @Test
    public void shouldReturnListOfMessagesWithNoElement() {

        Assert.assertTrue(messageRepository.getMessages().isEmpty());
    }
}
