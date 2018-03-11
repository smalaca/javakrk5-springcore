package com.smalaca.messagesender.repository.inmemory.jpa;

import com.smalaca.messagesender.domain.SlackStat;
import org.springframework.data.repository.CrudRepository;


public interface SlackStatsRepository extends CrudRepository<SlackStat, Integer> {

   // List<SlackStat> findFirst3ByMessageContainsAndMessageToContainsOrderByMessageToDesc(String messageFrom, String messageTo);


}
