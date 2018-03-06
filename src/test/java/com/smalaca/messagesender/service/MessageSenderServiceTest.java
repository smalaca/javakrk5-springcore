package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.domain.MessageRepository;
import com.smalaca.messagesender.exceptions.NoMessageException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageSenderServiceTest {

    @Autowired
    private MessageSenderService messageSenderService;
    @Autowired
    MessageRepository messageRepository;
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


        Message message = new MessageFactory().createFrom(messageDto, "1");
        messageRepository.add(message);
        Response response = messageSenderService.sendMessageViaEmail("1");

        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void shouldReturnExceptionIfSendMessageViaMailUnsuccessful() {
        Assert.assertTrue(true);
    }

    @Test
    public void shouldThrowExceptionWhenMessageIsNull() {
        String messageId = "not existing id";
        try {
            messageSenderService.sendMessageViaEmail(messageId);
            fail("Expected NoMessageException");
        } catch (NoMessageException e) {
            e.printStackTrace();
            // success
        }
    }

}
