package com.smalaca.messagesender;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.service.MessageCrud;
import com.smalaca.messagesender.service.MessageDto;
import com.smalaca.messagesender.service.Response;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@ImportResource({"classpath*:message-sender.xml"})
public class MessageSenderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageSenderApplication.class, args);
    }
}
