package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.EmailStat;
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
    private EmailStat es1 = new EmailStat("franek", "kamilek", "subject1", new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
    private EmailStat es2 = new EmailStat("franek", "wacek", "subject2", new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
    private EmailStat es3 = new EmailStat("franek", "robert", "subject3", new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
    private EmailStat es4 = new EmailStat("franek", "kacper", "subject4", new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
    private EmailStat es5 = new EmailStat("franek", "wiola", "subject5", new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
    private EmailStat es6 = new EmailStat("franek", "lucek", "subject6", new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
    private EmailStat es7 = new EmailStat("franek", "rysiek", "subject7", new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
    private EmailStat es8 = new EmailStat("franek", "mateo", "subject8", new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
    private EmailStat es9 = new EmailStat("franek", "rysio", "subject9", new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
    private EmailStat es10 = new EmailStat("franek", "juras", "subject10", new Timestamp(t1.getTime() + T1 + random.nextInt(DELTA)));
    private EmailStat es11 = new EmailStat("franek", "michaś", "subject11", new Timestamp(t1.getTime() + T2 + random.nextInt(DELTA)));
    private EmailStat es12 = new EmailStat("franek", "jaruś", "subject12", new Timestamp(t1.getTime() + T2 + random.nextInt(DELTA)));
    private EmailStat es13 = new EmailStat("franek", "tadzio", "subject13", new Timestamp(t1.getTime() + T3 + random.nextInt(DELTA)));
    private EmailStat es14 = new EmailStat("franek", "robercik", "subject14", new Timestamp(t1.getTime() + T3 + random.nextInt(DELTA)));
    private EmailStat es15 = new EmailStat("franek", "emilka", "subject15", new Timestamp(t1.getTime() + T3 + random.nextInt(DELTA)));

    @Autowired
    MessageStatsRepository repository;

    @Before
    public void setUp() throws Exception {
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