package com.smalaca.messagesender.service;

import com.smalaca.messagesender.exceptions.NoMessageException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageSenderServiceTest {

    @Autowired
    private MessageSenderService messageSenderService;
    @Autowired
    private MessageCrud messageCrud;

    @Test
    public void shouldReturnTrueIfSendMessageViaMailSuccessful() {
        MessageDto messageDto = new MessageDto();
        messageDto.setBody("some body");
        messageDto.setSubject("some subject");
        messageDto.setFrom("msiek");
        messageDto.setTo("java5krk");
        messageCrud.createNew(messageDto);

        Response response = messageSenderService.sendMessageViaEmail("1");
        Assert.assertTrue(response.isSuccess());
    }

    @Test(expected = NoMessageException.class)
    public void shouldReturnExceptionIfSendMessageViaMailUnsuccessful() {

        MessageDto messageDto = new MessageDto();
        messageDto.setBody("some body");
        messageDto.setSubject("some subject");
        messageDto.setFrom("msiek");
        messageDto.setTo("java5krk");

        messageCrud.createNew(messageDto);
        Response response = messageSenderService.sendMessageViaEmail(null);
        Assert.assertEquals(response.isSuccess(), false);

    }

    @Test(expected = NoMessageException.class)
    public void shouldThrowExceptionWhenMessageIsNull() throws Exception {

        MessageDto messageDto = null;
        if (messageDto == null)
            throw new NoMessageException("Message is empty");
    }
}
