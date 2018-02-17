package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
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
    @Autowired private MessageRepository messageRepository;
    @Autowired private MessageCrud messageCrud;

    @Test
    public void shouldCreateNewMessage() {
        String subject = "subject";
        String body = "body";
        String from = "from";
        String to = "to";

        Response response = messageCrud.createNew(subject, body, from, to);

        Assert.assertTrue(response.isSuccess());
        Assert.assertThat(response.getMessage(), any(String.class));
        Assert.assertTrue(messageRepository.exists(response.getMessage()));
    }

    @Test
    public void shouldNotCreateNewMessage() {
        String subject = "subject";
        String body = "body";
        String from = "from";
        String to = "to";
        messageRepository.add(new Message.MessageBuilder()
                .withBody(body)
                .withSubject(subject)
                .withFrom(from)
                .withTo(to)
                .build());

        Response response = messageCrud.createNew(subject, body, from, to);

        Assert.assertFalse(response.isSuccess());
    }
}
