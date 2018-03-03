package com.smalaca.messagesender;

import com.smalaca.messagesender.service.MessageCrud;
import com.smalaca.messagesender.service.MessageDto;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageSenderApplication {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("message-sender.xml");
        MessageCrud messageCrud = (MessageCrud) context.getBean("messageCrud");
        messageCrud.createNew(aMessageDto());

        context.registerShutdownHook();
    }

    private static MessageDto aMessageDto() {
        MessageDto messageDto = new MessageDto();
        messageDto.setBody("body");
        messageDto.setSubject("subject");
        messageDto.setFrom("from");
        messageDto.setTo("to");
        return messageDto;
    }
}
