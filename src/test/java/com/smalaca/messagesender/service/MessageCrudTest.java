package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.domain.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/message-sender.xml")
public class MessageCrudTest {
    public static final String SUBJECT = "subject";
    public static final String BODY = "body";
    public static final String FROM = "from";
    public static final String TO = "to";

    private MessageDto getMessageDto() {
        MessageDto messageDto = new MessageDto();
        messageDto.setBody(BODY);
        messageDto.setSubject(SUBJECT);
        messageDto.setFrom(FROM);
        messageDto.setTo(TO);
        return messageDto;
    }

    @Autowired private MessageRepository messageRepository;
    @Autowired private MessageCrud messageCrud;

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
        Assert.assertThat(response.getMessage(), any(String.class));
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
