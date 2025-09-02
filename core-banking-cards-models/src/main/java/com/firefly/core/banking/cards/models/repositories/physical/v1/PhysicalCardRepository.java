package com.firefly.core.banking.cards.models.repositories.physical.v1;

import com.firefly.core.banking.cards.models.entities.physical.v1.PhysicalCard;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface PhysicalCardRepository extends BaseRepository<PhysicalCard, UUID> {
    Mono<PhysicalCard> findByPhysicalCardId(UUID physicalCardId);

    Flux<PhysicalCard> findByCardId(UUID cardId, Pageable pageable);
    Mono<Long> countByCardId(UUID cardId);
}