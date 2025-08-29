package com.firefly.core.banking.cards.models.repositories.physical.v1;

import com.firefly.core.banking.cards.models.entities.physical.v1.PhysicalCard;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PhysicalCardRepository extends BaseRepository<PhysicalCard, Long> {
    Mono<PhysicalCard> findByPhysicalCardId(Long physicalCardId);

    Flux<PhysicalCard> findByCardId(Long cardId, Pageable pageable);
    Mono<Long> countByCardId(Long cardId);
}