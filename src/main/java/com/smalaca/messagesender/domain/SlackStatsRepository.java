package com.smalaca.messagesender.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface SlackStatsRepository extends CrudRepository<Stat, Integer> {

    Optional<List<Stat>> findFirst3ByMessageFromContainsAndMessageToContainsOrderByMessageToDesc(String messageFrom, String messageTo);
}
