package com.smalaca.messagesender.service;


import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.domain.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FakeMailSenderTest {
    @Autowired
    FakeMailSender fakeMailSender;
    @Autowired
    MessageCrud messageCrud;
    @Autowired
    private MessageRepository messageRepository;

    private final String SUBJECT = "Subject";
    private final String BODY = "Body";
    private final String FROM = "from";
    private final String TO = "to";

    @Test
    public void shouldReturnTrueWhenMessageIsSuccess() {

        MessageDto messageDto = new MessageDto();
        messageDto.setSubject(SUBJECT);
        messageDto.setTo(TO);
        messageDto.setFrom(FROM);
        messageDto.setBody(BODY);
        messageCrud.createNew(messageDto);

        Message message = new MessageFactory().createFrom(messageDto, "1");
        Assert.assertTrue(fakeMailSender.sendMessage(message).isSuccess());
    }
}