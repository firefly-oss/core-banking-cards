package com.firefly.core.banking.cards.models.repositories.configuration.v1;

import com.firefly.core.banking.cards.models.entities.configuration.v1.CardConfiguration;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface CardConfigurationRepository extends BaseRepository<CardConfiguration, UUID> {
    Mono<CardConfiguration> findByCardConfigurationId(UUID cardConfigurationId);

    Flux<CardConfiguration> findByCardId(UUID cardId, Pageable pageable);
    Mono<Long> countByCardId(UUID cardId);
}