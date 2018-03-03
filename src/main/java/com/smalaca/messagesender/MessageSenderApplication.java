package com.smalaca.messagesender;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.service.MessageCrud;
import com.smalaca.messagesender.service.MessageDto;
import com.smalaca.messagesender.service.Response;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageSenderApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("message-sender.xml");
        MessageCrud messageCrud = (MessageCrud)     context.getBean("messageCrud");
        String subject = "subject";
        String body = "body";
        String from = "from";
        String to = "to";

        MessageDto messageDto = new MessageDto();
        messageDto.setBody(body);
        messageDto.setSubject(subject);
        messageDto.setFrom(from);
        messageDto.setTo(to);

        Response response = messageCrud.createNew(messageDto);
        System.out.println(response.isSuccess());
        System.out.println(response.getMessage());

        response = messageCrud.createNew(messageDto);
        System.out.println(response.isSuccess());
        System.out.println(response.getMessage());
    }
}
