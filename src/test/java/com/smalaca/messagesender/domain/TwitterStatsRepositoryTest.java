package com.smalaca.messagesender.domain;

import com.smalaca.messagesender.service.MessageDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TwitterStatsRepositoryTest {

    private static final String FROM = "from";
    private static final String TO = "to";
    private static final String SOME_ID = "13";

    @Autowired TwitterStatsRepository twitterStatsRepository;

    @Test
    public void shouldStatexists(){
    assertFalse(twitterStatsRepository.exists(SOME_ID));
    }
}