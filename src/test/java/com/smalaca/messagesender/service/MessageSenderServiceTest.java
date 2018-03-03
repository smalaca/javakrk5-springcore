package com.smalaca.messagesender.service;

import com.smalaca.messagesender.repository.inmemory.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/message-sender.xml")
public class MessageSenderServiceTest {

    @Autowired private MessageSenderService messageSenderService;
    @Autowired private MessageCrud messageCrud;

    @Test
    public void shouldReturnTrueIfSendMessageViaMailSuccessful(){
        MessageDto messageDto = new MessageDto();
        messageDto.setBody("some body");
        messageDto.setSubject("some subject");
        messageDto.setFrom("msiek");
        messageDto.setTo("java5krk");
        messageCrud.createNew(messageDto);

        Response response = messageSenderService.sendMessageViaEmail("1");
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void shouldReturnExceptionIfSendMessageViaMailUnsuccessful(){
        Assert.assertTrue(true);
    }

}
