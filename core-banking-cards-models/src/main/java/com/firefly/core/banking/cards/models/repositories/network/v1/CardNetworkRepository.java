package com.firefly.core.banking.cards.models.repositories.network.v1;

import com.firefly.core.banking.cards.models.entities.network.v1.CardNetwork;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository for managing CardNetwork entities.
 */
@Repository
public interface CardNetworkRepository extends BaseRepository<CardNetwork, Long> {
    /**
     * Find a CardNetwork by its ID.
     *
     * @param cardNetworkId the ID of the CardNetwork to find
     * @return a Mono emitting the CardNetwork if found, or empty if not found
     */
    Mono<CardNetwork> findByCardNetworkId(Long cardNetworkId);
    
    /**
     * Find a CardNetwork by its code.
     *
     * @param networkCode the code of the CardNetwork to find
     * @return a Mono emitting the CardNetwork if found, or empty if not found
     */
    Mono<CardNetwork> findByNetworkCode(String networkCode);
    
    /**
     * Find a CardNetwork by its name.
     *
     * @param networkName the name of the CardNetwork to find
     * @return a Mono emitting the CardNetwork if found, or empty if not found
     */
    Mono<CardNetwork> findByNetworkName(String networkName);
    
    /**
     * Find active CardNetworks.
     *
     * @return a Flux emitting all active CardNetworks
     */
    Flux<CardNetwork> findByIsActiveTrue();
}