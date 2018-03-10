package com.smalaca.messagesender.domain;

import org.springframework.data.repository.CrudRepository;

public interface SlackStatsRepository extends CrudRepository<Stat, Integer> {
}
