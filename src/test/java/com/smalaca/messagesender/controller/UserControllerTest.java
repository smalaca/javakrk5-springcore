package com.smalaca.messagesender.controller;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldCreateNewUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/create?login=andrzej&email=email").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("User Created")));
    }

    @Test
    public void shouldNotCreateNewUserWhenNoParametersPassed() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/create").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("User Creation Failed")));
    }

    @Test
    public void shouldNotCreateNewUserWhenTryToAddSameUserAgain() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user/create?login=andrzej1&email=email").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("User Created")));
        mvc.perform(MockMvcRequestBuilders.get("/user/create?login=andrzej1&email=email").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("User Creation Failed")));
    }


}
