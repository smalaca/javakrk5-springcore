package com.smalaca.messagesender.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SlackStatsRepository extends CrudRepository<Stat, Integer> {

    Optional<Stat> findAllByName(String from, String to);
}
