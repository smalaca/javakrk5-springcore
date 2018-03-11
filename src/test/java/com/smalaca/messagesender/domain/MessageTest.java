package com.smalaca.messagesender.domain;

import com.smalaca.messagesender.service.MessageDto;
import org.junit.Assert;
import org.junit.Test;

public class MessageTest {
    @Test
    public void shouldCreateMessageWithRequiredFields() {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject("subject");
        messageDto.setBody("body");
        messageDto.setFrom("from");
        messageDto.setTo("to");

        Message message = new MessageFactory().createFromWithoutId(messageDto);

        Assert.assertEquals(messageDto.getSubject(), message.getSubject());
        Assert.assertEquals(messageDto.getBody(), message.getBody());
        Assert.assertEquals(messageDto.getFrom(), message.getSender());
        Assert.assertEquals(messageDto.getTo(), message.getTo());
    }
}
