package com.smalaca.messagesender.service;


import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailSenderTest {


    @Autowired
    MailSender mailSender;

    @Autowired
    MessageCrud messageCrud;
    @Autowired
    private MessageRepository messageRepository;


    @Test
    public void shouldReturnTrueWhenMessageIsSuccess() {

        MessageDto messageDto = new MessageDto();
        messageDto.setSubject("DaDA");
        messageDto.setTo("to");
        messageDto.setFrom("from");
        messageDto.setBody("body");

        Response response = messageCrud.createNew(messageDto);
        Message message = messageRepository.getMessageById("1");

        Assert.assertTrue(mailSender.sendEmailSender(message).isSuccess());
    }


}


