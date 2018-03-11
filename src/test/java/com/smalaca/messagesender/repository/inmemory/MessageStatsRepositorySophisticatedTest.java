package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.EmailStat;
import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.service.MessageDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MessageStatsRepositorySophisticatedTest {
    public static final int DELTA = 5000;
    public static final int T1 = 10000;
    public static final int T2 = 20000;
    public static final int T3 = 80000;
    private Timestamp t1 = new Timestamp(System.currentTimeMillis());
    private Random random = new Random();
    MessageDto mdto = new MessageDto();
    MessageFactory factory = new MessageFactory();

    @Autowired
    MessageStatsRepository repository;

    @Before
    public void setUp() throws Exception {
        mdto.setFrom("franek");
        mdto.setTo("kamilek");
        mdto.setSubject("subject1");
        EmailStat es1 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
        mdto.setTo("wacek");
        mdto.setSubject("subject2");
        EmailStat es2 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
        EmailStat es3 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
        EmailStat es4 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
        EmailStat es5 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
        EmailStat es6 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
        EmailStat es7 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
        EmailStat es8 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
        EmailStat es9 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
        EmailStat es10 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
        EmailStat es11 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T2 + random.nextInt(DELTA)));
        EmailStat es12 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T2 + random.nextInt(DELTA)));
        EmailStat es13 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T3 + random.nextInt(DELTA)));
        EmailStat es14 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T3 + random.nextInt(DELTA)));
        EmailStat es15 = new EmailStat(factory.createFrom(mdto), new Timestamp(t1.getTime() + T3 + random.nextInt(DELTA)));

        repository.save(es1);
        repository.save(es2);
        repository.save(es3);
        repository.save(es4);
        repository.save(es5);
        repository.save(es6);
        repository.save(es7);
        repository.save(es8);
        repository.save(es9);
        repository.save(es10);
        repository.save(es11);
        repository.save(es12);
        repository.save(es13);
        repository.save(es14);
        repository.save(es15);
    }

    @Test
    public void shouldFind3Messages() {
        List<EmailStat> results = repository.findTop3ByDateAfterAndDateBeforeAndFromContainsOrderByFromAsc(t1, new Timestamp(t1.getTime() + 85000), "fran");
        Assert.assertEquals(3, results.size());
    }

    @Test
    public void shouldFind2Messages() {
        List<EmailStat> results = repository.findTop3ByDateAfterAndDateBeforeAndFromContainsOrderByFromAsc(new Timestamp(t1.getTime() + 20000), new Timestamp(t1.getTime() + 25000), "fran");
        results.forEach(System.out::println);
        Assert.assertEquals(2, results.size());
    }

    @Test
    public void shouldFind0Messages() {
        List<EmailStat> results = repository.findTop3ByDateAfterAndDateBeforeAndFromContainsOrderByFromAsc(new Timestamp(t1.getTime() + 20000), new Timestamp(t1.getTime() + 75000), "franks");
        Assert.assertEquals(0, results.size());
    }

}