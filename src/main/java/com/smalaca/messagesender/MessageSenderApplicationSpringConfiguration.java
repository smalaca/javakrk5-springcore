package com.smalaca.messagesender;

import com.smalaca.messagesender.domain.MessageRepository;
import com.smalaca.messagesender.repository.inmemory.InMemoryMessageRepository;
import com.smalaca.messagesender.service.MessageCrud;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageSenderApplicationSpringConfiguration {
    @Bean(name="messageCrud")
    public MessageCrud gimmeMeAMessageCrud() {
        return new MessageCrud(messageRepository());
    }

    @Bean
    public MessageRepository messageRepository() {
        return new InMemoryMessageRepository();
    }
}
