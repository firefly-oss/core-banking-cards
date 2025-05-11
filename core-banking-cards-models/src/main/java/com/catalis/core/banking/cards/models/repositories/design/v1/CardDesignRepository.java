package com.catalis.core.banking.cards.models.repositories.design.v1;

import com.catalis.core.banking.cards.models.entities.design.v1.CardDesign;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository for managing CardDesign entities.
 */
@Repository
public interface CardDesignRepository extends BaseRepository<CardDesign, Long> {
    /**
     * Find a CardDesign by its ID.
     *
     * @param designId the ID of the CardDesign to find
     * @return a Mono emitting the CardDesign if found, or empty if not found
     */
    Mono<CardDesign> findByDesignId(Long designId);
    
    /**
     * Find a CardDesign by its code.
     *
     * @param designCode the code of the CardDesign to find
     * @return a Mono emitting the CardDesign if found, or empty if not found
     */
    Mono<CardDesign> findByDesignCode(String designCode);
    
    /**
     * Find CardDesigns by card type ID.
     *
     * @param cardTypeId the card type ID to search for
     * @return a Flux emitting the CardDesigns for the specified card type
     */
    Flux<CardDesign> findByCardTypeId(Long cardTypeId);
    
    /**
     * Find CardDesigns by issuer ID.
     *
     * @param issuerId the issuer ID to search for
     * @return a Flux emitting the CardDesigns for the specified issuer
     */
    Flux<CardDesign> findByIssuerId(Long issuerId);
    
    /**
     * Find CardDesigns by card network ID.
     *
     * @param cardNetworkId the card network ID to search for
     * @return a Flux emitting the CardDesigns for the specified card network
     */
    Flux<CardDesign> findByCardNetworkId(Long cardNetworkId);
    
    /**
     * Find active CardDesigns.
     *
     * @return a Flux emitting all active CardDesigns
     */
    Flux<CardDesign> findByIsActiveTrue();
    
    /**
     * Find default CardDesigns.
     *
     * @return a Flux emitting all default CardDesigns
     */
    Flux<CardDesign> findByIsDefaultTrue();
    
    /**
     * Find customizable CardDesigns.
     *
     * @return a Flux emitting all customizable CardDesigns
     */
    Flux<CardDesign> findByIsCustomizableTrue();
}