package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.MessageRepository;
import com.smalaca.messagesender.repository.inmemory.InMemoryMessageRepository;
import org.junit.Assert;
import org.junit.Before;
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

        String id = messageCrud.createNew(subject, body, from, to);

        Assert.assertThat(id, any(String.class));
        Assert.assertTrue(messageRepository.exists(id));
    }
}
