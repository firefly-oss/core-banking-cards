package com.catalis.core.banking.cards.models.repositories.configuration.v1;

import com.catalis.core.banking.cards.models.entities.configuration.v1.CardConfiguration;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CardConfigurationRepository extends BaseRepository<CardConfiguration, Long> {
    Mono<CardConfiguration> findByCardConfigurationId(Long cardConfigurationId);

    Flux<CardConfiguration> findByCardId(Long cardId, Pageable pageable);
    Mono<Long> countByCardId(Long cardId);
}