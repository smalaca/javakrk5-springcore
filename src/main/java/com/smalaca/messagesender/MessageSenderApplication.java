package com.smalaca.messagesender;

import com.smalaca.messagesender.service.MessageCrud;
import com.smalaca.messagesender.service.MessageDto;
import com.smalaca.messagesender.service.Response;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageSenderApplication {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "production");
        ApplicationContext context = new ClassPathXmlApplicationContext("message-sender.xml");
        MessageCrud messageCrud = (MessageCrud) context.getBean("messageCrud");
        MessageDto messageDto = aMassageDto();

        displayResponse(messageCrud.createNew(messageDto));
        displayResponse(messageCrud.createNew(messageDto));
    }

    private static void displayResponse(Response response) {
        System.out.println(response.isSuccess());
        System.out.println(response.getMessage());
    }

    private static MessageDto aMassageDto() {
        MessageDto messageDto = new MessageDto();
        String subject = "subject";
        String body = "body";
        String from = "from";
        String to = "to";

        messageDto.setBody(body);
        messageDto.setSubject(subject);
        messageDto.setFrom(from);
        messageDto.setTo(to);
        return messageDto;
    }
}
