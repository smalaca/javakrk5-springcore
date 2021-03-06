package com.smalaca.messagesender.controller;

import com.smalaca.messagesender.domain.Message;
import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.domain.MessageRepository;
import com.smalaca.messagesender.service.MessageDto;
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
public class MessageCrudControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private MessageRepository repository;
    private MessageFactory factory = new MessageFactory();

    @Test
    public void shouldReceiveResponseForDeletionOfNonExistingMessage() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/message/delete")
                        .param("id", "1"))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("{\"success\":false,\"message\":\"Message with id: 1 does not exist.\"}", response.getContentAsString());
    }

    @Test
    public void shouldReceiveResponseForDeletionOfExistingMessage() throws Exception {

        MessageDto messageDto = new MessageDto();
        messageDto.setSubject("some subject 2");
        messageDto.setBody("some body 2");
        messageDto.setTo("smalaca");
        messageDto.setFrom("javakrk5");

        Message message = factory.createFrom(messageDto, "777");
        repository.add(message);

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/message/delete")
                        .param("id", "777"))
                .andReturn().getResponse();

        System.out.println(response.getContentAsString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("{\"success\":true,\"message\":null}" , response.getContentAsString());
    }

    @Test
    public void shouldReceiveResponseForMessageDeletionWithoutIdParameter() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/message/delete"))
                .andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void shouldReceiveResponseWithListOfMessagesThatContainsSpecificMessage() throws Exception {

        MessageDto messageDto = new MessageDto();
        messageDto.setSubject("some subject 2");
        messageDto.setBody("some body 2");
        messageDto.setTo("smalaca");
        messageDto.setFrom("javakrk5");

        Message message = factory.createFrom(messageDto, "999");
        repository.add(message);

        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/message/all"))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("[{\"subject\":\"some subject 2\",\"body\":\"some body 2\",\"sender\":\"javakrk5\",\"to\":\"smalaca\",\"id\":\"999\"}]", response.getContentAsString());
    }
}