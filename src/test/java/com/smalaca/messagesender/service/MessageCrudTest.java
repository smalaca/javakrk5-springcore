package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Matchers.isA;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/message-sender.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class MessageCrudTest {
    private static final String MESSAGE_FROM = "from";
    private static final String MESSAGE_TO = "to";
    private static final String BODY = "body";
    private static final String SUBJECT = "subject";
    @Autowired private MessageRepository messageRepository;
    @Autowired private MessageCrud messageCrud;

    @Test
    public void shouldCreateNewMessage() {
        Response response = messageCrud.createNew(aMessageDto());

        Assert.assertTrue(response.isSuccess());
        Assert.assertThat(response.getMessage(), any(String.class));
        ArgumentCaptor<Message> messageArgumentCaptor  = ArgumentCaptor.forClass(Message.class);
        BDDMockito.then(messageRepository).should().add(messageArgumentCaptor.capture());
        assertCreatedMessage(messageArgumentCaptor.getValue());
    }

    private void assertCreatedMessage(Message message) {
        Assert.assertEquals(SUBJECT, message.getSubject());
        Assert.assertEquals(BODY, message.getBody());
        Assert.assertEquals(MESSAGE_FROM, message.getFrom());
        Assert.assertEquals(MESSAGE_TO, message.getTo());
    }

    @Test
    public void shouldNotCreateNewMessage() {
        BDDMockito.given(messageRepository.exists(isA(Message.class))).willReturn(true);

        Response response = messageCrud.createNew(aMessageDto());

        Assert.assertFalse(response.isSuccess());
        BDDMockito.then(messageRepository).should(Mockito.never()).add(isA(Message.class));
    }

    private MessageDto aMessageDto() {
        MessageDto messageDto = new MessageDto();
        messageDto.setFrom(MESSAGE_FROM);
        messageDto.setTo(MESSAGE_TO);
        messageDto.setBody(BODY);
        messageDto.setSubject(SUBJECT);
        return messageDto;
    }
}
