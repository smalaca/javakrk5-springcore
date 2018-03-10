package com.smalaca.messagesender.repository.inmemory.jpa;

import com.smalaca.messagesender.domain.SlackStat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SlackStatsRepository extends CrudRepository<SlackStat, Integer> {

    List<SlackStat> findFirst3ByMessageFromContainsAndMessageToContainsOrderByMessageToDesc(String messageFrom, String messageTo);

}
