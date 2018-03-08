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

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FakeTwitterSenderTest {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    FakeTwitterSender fakeTwitterSender;
    @Autowired
    MessageCrud messageCrud;

    private final String SUBJECT = "Subject";
    private final String BODY = "Body";
    private final String FROM = "from";
    private final String TO = "to";

    @Test
    public void shouldReturnTrueIfTwittIsSendSuccessfully() {

        MessageDto messageDto = new MessageDto();
        messageDto.setSubject(SUBJECT);
        messageDto.setBody(BODY);
        messageDto.setFrom(FROM);
        messageDto.setFrom(TO);

        messageCrud.createNew(messageDto);

        Message message = new MessageFactory().createFrom(messageDto, "1");
        Assert.assertEquals(fakeTwitterSender.sendMessage(message).isSuccess(), true);
    }
}
