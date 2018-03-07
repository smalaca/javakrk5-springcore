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
public class FakeSlackSenderTest {


    private final String SUBJECT = "Subject";
    private final String BODY = "Body";
    private final String FROM = "from";
    private final String TO = "to";

    @Autowired
    FakeSlackSender fakeSlackSender;
    @Autowired
    private MessageCrud messageCrud;
    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void shouldReturnTrueIfSlackMessegeWasSuccessfullSended() {

        MessageDto messageDto = new MessageDto();
        messageDto.setSubject(SUBJECT);
        messageDto.setBody(BODY);
        messageDto.setFrom(FROM);
        messageDto.setTo(TO);

        messageCrud.createNew(messageDto);

        Message message = new MessageFactory().createFrom(messageDto, "1");

        Assert.assertEquals(fakeSlackSender.sendMessage(message).isSuccess(), true);
    }
}
