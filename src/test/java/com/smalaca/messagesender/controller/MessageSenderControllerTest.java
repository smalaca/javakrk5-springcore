package com.smalaca.messagesender.controller;

import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.domain.MessageRepository;
import com.smalaca.messagesender.service.MessageDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessageSenderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MessageRepository repository;

    @Test
    public void shouldSendMessageSuccessful() throws Exception {
        MessageDto messageDto = new MessageDto();
        messageDto.setBody("some body");
        messageDto.setSubject("some subject");
        messageDto.setFrom("msiek");
        messageDto.setTo("java5krk");
        repository.add(new MessageFactory().createFrom(messageDto, "1"));
        Assert.assertTrue(repository.exists("1"));

        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/send/1")
                        .param("id", "1"))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("{\"success\":true,\"message\":\"OK\"}", response.getContentAsString());
    }
}
