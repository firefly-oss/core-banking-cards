package com.catalis.core.banking.cards.models.repositories.issuer.v1;

import com.catalis.core.banking.cards.models.entities.issuer.v1.Issuer;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository for managing Issuer entities.
 */
@Repository
public interface IssuerRepository extends BaseRepository<Issuer, Long> {
    /**
     * Find an Issuer by its ID.
     *
     * @param issuerId the ID of the Issuer to find
     * @return a Mono emitting the Issuer if found, or empty if not found
     */
    Mono<Issuer> findByIssuerId(Long issuerId);
    
    /**
     * Find an Issuer by its code.
     *
     * @param issuerCode the code of the Issuer to find
     * @return a Mono emitting the Issuer if found, or empty if not found
     */
    Mono<Issuer> findByIssuerCode(String issuerCode);
    
    /**
     * Find Issuers by country code.
     *
     * @param countryCode the country code to search for
     * @return a Flux emitting the Issuers for the specified country
     */
    Flux<Issuer> findByCountryCode(String countryCode);
    
    /**
     * Find active Issuers.
     *
     * @return a Flux emitting all active Issuers
     */
    Flux<Issuer> findByIsActiveTrue();
}