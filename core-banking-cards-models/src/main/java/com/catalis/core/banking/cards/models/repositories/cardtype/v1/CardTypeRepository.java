package com.catalis.core.banking.cards.models.repositories.cardtype.v1;

import com.catalis.core.banking.cards.models.entities.cardtype.v1.CardType;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository for managing CardType entities.
 */
@Repository
public interface CardTypeRepository extends BaseRepository<CardType, Long> {
    /**
     * Find a CardType by its ID.
     *
     * @param cardTypeId the ID of the CardType to find
     * @return a Mono emitting the CardType if found, or empty if not found
     */
    Mono<CardType> findByCardTypeId(Long cardTypeId);
    
    /**
     * Find a CardType by its code.
     *
     * @param typeCode the code of the CardType to find
     * @return a Mono emitting the CardType if found, or empty if not found
     */
    Mono<CardType> findByTypeCode(String typeCode);
    
    /**
     * Find a CardType by its name.
     *
     * @param typeName the name of the CardType to find
     * @return a Mono emitting the CardType if found, or empty if not found
     */
    Mono<CardType> findByTypeName(String typeName);
    
    /**
     * Find active CardTypes.
     *
     * @return a Flux emitting all active CardTypes
     */
    Flux<CardType> findByIsActiveTrue();
    
    /**
     * Find credit card types.
     *
     * @return a Flux emitting all credit card types
     */
    Flux<CardType> findByIsCreditTrue();
    
    /**
     * Find debit card types.
     *
     * @return a Flux emitting all debit card types
     */
    Flux<CardType> findByIsDebitTrue();
    
    /**
     * Find prepaid card types.
     *
     * @return a Flux emitting all prepaid card types
     */
    Flux<CardType> findByIsPrepaidTrue();
}