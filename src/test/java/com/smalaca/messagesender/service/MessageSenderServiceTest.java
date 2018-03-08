package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.domain.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageSenderServiceTest {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    MessageCrud messageCrud;
    @Autowired
    MessageSenderService messageSenderService;

    private final String SUBJECT = "Subject";
    private final String BODY = "Body";
    private final String FROM = "From";
    private final String TO = "To";


    @Test
    public void shouldReturnTrueIfMessageViaEmailWasSendSuccessfull() {

        MessageDto messageDto = new MessageDto();
        messageDto.setSubject(SUBJECT);
        messageDto.setBody(BODY);
        messageDto.setFrom(FROM);
        messageDto.setTo(TO);

        messageCrud.createNew(messageDto);
        Message message = new MessageFactory().createFrom(messageDto, "1");
        messageRepository.add(message);

        Response response = messageSenderService.sendMessageViaEmail("1");
        assertTrue(response.isSuccess());


    }

}
