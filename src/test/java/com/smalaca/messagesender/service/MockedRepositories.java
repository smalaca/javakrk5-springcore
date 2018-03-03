package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.MessageRepository;
import com.smalaca.messagesender.repository.inmemory.InMemoryMessageRepository;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class MockedRepositories {

    @Bean
    public MessageRepository messageRepository() {
        return Mockito.mock(MessageRepository.class);
    }
}
