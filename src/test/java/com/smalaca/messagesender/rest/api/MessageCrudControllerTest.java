package com.smalaca.messagesender.rest.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessageCrudControllerTest {
    private static final String SOME_SUBJECT = "some subject";
    private static final String SOME_BODY = "fancy package";
    private static final String MESSAGE_FROM = "from@me";
    private static final String MESSAGE_TO = "to@you";
    private static final String DUMMY_VALUE = "dummy";
    public static final String NOT_EXISTING_ID = "69";

    @Autowired private MockMvc mvc;

    @Test
    public void shouldAddMessage() throws Exception {
        MockHttpServletResponse result = mvc.perform(
                MockMvcRequestBuilders.get("/message/add").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals("Added", result.getContentAsString());
    }

    @Test
    public void shouldReadWithIdAsParam() throws Exception {
        String uniqueIdentifier = "13";
        MockHttpServletResponse result = mvc.perform(
                MockMvcRequestBuilders.get("/message/get")
                        .param("id", uniqueIdentifier)
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals("Message with id: " + uniqueIdentifier, result.getContentAsString());
    }

    @Test
    public void shouldNotGetUserWithGivenId() throws Exception {
        MockHttpServletResponse result = mvc.perform(
                MockMvcRequestBuilders.get("/message/get")
                        .param("id", NOT_EXISTING_ID)
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatus());
    }

    @Test
    public void shouldReadWithIdInPath() throws Exception {
        String uniqueIdentifier = "13";
        MockHttpServletResponse result = mvc.perform(
                MockMvcRequestBuilders.get("/message/find/{id}", uniqueIdentifier)
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals("Found message with id: " + uniqueIdentifier, result.getContentAsString());
    }

    @Test
    public void shouldNotFoundUserWithGivenId() throws Exception {
        MockHttpServletResponse result = mvc.perform(
                MockMvcRequestBuilders.get("/message/find/{id}", NOT_EXISTING_ID)
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatus());
    }

    @Test
    public void shouldReturnMessageById() throws Exception {
        String uniqueIdentifier = "13";
        MockHttpServletResponse result = mvc.perform(
                MockMvcRequestBuilders.get("/message/find-by/{id}", uniqueIdentifier)
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals("{\"body\":\"body\",\"subject\":\"subject\",\"from\":\"\",\"to\":\"\"}", result.getContentAsString());
    }

    @Test
    public void shouldRecognizeThatMessageWithGivenIdDoesNotExist() throws Exception {
        MockHttpServletResponse result = mvc.perform(
                MockMvcRequestBuilders.get("/message/find-by/{id}", NOT_EXISTING_ID)
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatus());
        assertEquals("Message with id: " + NOT_EXISTING_ID + " does not exist.", result.getContentAsString());
    }

    @Test
    public void shouldDeleteWithBothParameters() throws Exception {
        String name = "smalaca";
        String uniqueIdentifier = "13";
        MockHttpServletResponse result = mvc.perform(
                MockMvcRequestBuilders.delete("/message/delete")
                        .param("id", uniqueIdentifier)
                        .param("name", name)
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals("Hi " + name + ". Message with id: " + uniqueIdentifier + " removed.", result.getContentAsString());
    }

    @Test
    public void shouldDeleteWithOnlyOneParameters() throws Exception {
        String uniqueIdentifier = "13";
        MockHttpServletResponse result = mvc.perform(
                MockMvcRequestBuilders.delete("/message/delete")
                        .param("id", uniqueIdentifier)
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals("Hi NO_NAME. Message with id: " + uniqueIdentifier + " removed.", result.getContentAsString());
    }

    @Test
    public void shouldDeleteWithoutParameters() throws Exception {
        MockHttpServletResponse result = mvc.perform(
                MockMvcRequestBuilders.delete("/message/delete")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());
    }

    @Test
    public void shouldDeleteWithDifferentRequestMethod() throws Exception {
        String uniqueIdentifier = "13";
        MockHttpServletResponse result = mvc.perform(
                MockMvcRequestBuilders.post("/message/delete")
                        .param("id", uniqueIdentifier)
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), result.getStatus());
    }

    @Test
    public void shouldUpdateWithAllParameters() throws Exception {
        MockHttpServletResponse result = mvc.perform(MockMvcRequestBuilders.post("/message/update")
                .param("subject", SOME_SUBJECT)
                .param("body", SOME_BODY)
                .param("from", MESSAGE_FROM)
                .param("to", MESSAGE_TO)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals(
                "Updated with subject: " + SOME_SUBJECT + ", body: " + SOME_BODY + ", from: " + MESSAGE_FROM +", to: " + MESSAGE_TO,
                result.getContentAsString());
    }

    @Test
    public void shouldUpdateWithSomeParameters() throws Exception {
        MockHttpServletResponse result = mvc.perform(MockMvcRequestBuilders.post("/message/update")
                .param("subject", SOME_SUBJECT)
                .param("body", SOME_BODY)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals(
                "Updated with subject: " + SOME_SUBJECT + ", body: " + SOME_BODY + ", from: , to: ",
                result.getContentAsString());
    }

    @Test
    public void shouldUpdateWithMoreParameters() throws Exception {
        MockHttpServletResponse result = mvc.perform(MockMvcRequestBuilders.post("/message/update")
                .param("subject", SOME_SUBJECT)
                .param("body", SOME_BODY)
                .param("something", DUMMY_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals(
                "Updated with subject: " + SOME_SUBJECT + ", body: " + SOME_BODY + ", from: , to: ",
                result.getContentAsString());
    }
}
