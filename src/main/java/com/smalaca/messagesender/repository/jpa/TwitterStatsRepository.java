package com.smalaca.messagesender.repository.jpa;

import com.smalaca.messagesender.domain.TwitterStats;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TwitterStatsRepository extends CrudRepository<TwitterStats, String> {

    List<TwitterStats> findFirst3ByFromContainsOrToContainsOrderByDateDesc(String from, String to);

}