package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.domain.MessageRepository;
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
        messageCrud.createNew(messageDto);

        Response response = messageCrud.deleteMessage("1");

        Assert.assertTrue(response.isSuccess());
        Assert.assertFalse(messageRepository.exists("1"));
    }

    @Test
    public void shouldNotDeletePreviouslyCreatedNewMessage() {

        MessageDto messageDto = getMessageDto();
        messageCrud.createNew(messageDto);

        Response response = messageCrud.deleteMessage("2");
        Assert.assertFalse(response.isSuccess());
        Assert.assertThat(response.getMessage(), any(String.class));
        Assert.assertTrue(messageRepository.exists("1"));
    }
}
