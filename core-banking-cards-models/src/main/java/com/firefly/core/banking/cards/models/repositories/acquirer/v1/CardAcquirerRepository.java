package com.firefly.core.banking.cards.models.repositories.acquirer.v1;

import com.firefly.core.banking.cards.models.entities.acquirer.v1.CardAcquirer;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for managing CardAcquirer entities.
 */
@Repository
public interface CardAcquirerRepository extends BaseRepository<CardAcquirer, Long> {
    /**
     * Find a CardAcquirer by its ID.
     *
     * @param acquirerId the ID of the CardAcquirer to find
     * @return a Mono emitting the CardAcquirer if found, or empty if not found
     */
    Mono<CardAcquirer> findByAcquirerId(Long acquirerId);
    
    /**
     * Find a CardAcquirer by its reference.
     *
     * @param acquirerReference the reference of the CardAcquirer to find
     * @return a Mono emitting the CardAcquirer if found, or empty if not found
     */
    Mono<CardAcquirer> findByAcquirerReference(String acquirerReference);
    
    /**
     * Find CardAcquirers by acquirer name.
     *
     * @param acquirerName the acquirer name to search for
     * @return a Flux emitting the CardAcquirers with the specified name
     */
    Flux<CardAcquirer> findByAcquirerName(String acquirerName);
    
    /**
     * Find CardAcquirers by acquirer legal name.
     *
     * @param acquirerLegalName the acquirer legal name to search for
     * @return a Flux emitting the CardAcquirers with the specified legal name
     */
    Flux<CardAcquirer> findByAcquirerLegalName(String acquirerLegalName);
    
    /**
     * Find CardAcquirers by acquirer code.
     *
     * @param acquirerCode the acquirer code to search for
     * @return a Flux emitting the CardAcquirers with the specified code
     */
    Flux<CardAcquirer> findByAcquirerCode(String acquirerCode);
    
    /**
     * Find CardAcquirers by acquirer type.
     *
     * @param acquirerType the acquirer type to search for
     * @return a Flux emitting the CardAcquirers of the specified type
     */
    Flux<CardAcquirer> findByAcquirerType(String acquirerType);
    
    /**
     * Find CardAcquirers by acquirer status.
     *
     * @param acquirerStatus the acquirer status to search for
     * @return a Flux emitting the CardAcquirers with the specified status
     */
    Flux<CardAcquirer> findByAcquirerStatus(String acquirerStatus);
    
    /**
     * Find active CardAcquirers.
     *
     * @return a Flux emitting all active CardAcquirers
     */
    Flux<CardAcquirer> findByIsActiveTrue();
    
    /**
     * Find CardAcquirers by country.
     *
     * @param country the country to search for
     * @return a Flux emitting the CardAcquirers from the specified country
     */
    Flux<CardAcquirer> findByCountry(String country);
    
    /**
     * Find CardAcquirers by supported card networks.
     *
     * @param cardNetwork the card network to search for
     * @return a Flux emitting the CardAcquirers supporting the specified card network
     */
    Flux<CardAcquirer> findBySupportedCardNetworksContaining(String cardNetwork);
    
    /**
     * Find CardAcquirers by supported currencies.
     *
     * @param currencyCode the currency code to search for
     * @return a Flux emitting the CardAcquirers supporting the specified currency
     */
    Flux<CardAcquirer> findBySupportedCurrenciesContaining(String currencyCode);
    
    /**
     * Find CardAcquirers by supported countries.
     *
     * @param countryCode the country code to search for
     * @return a Flux emitting the CardAcquirers supporting the specified country
     */
    Flux<CardAcquirer> findBySupportedCountriesContaining(String countryCode);
    
    /**
     * Find international CardAcquirers.
     *
     * @return a Flux emitting all international CardAcquirers
     */
    Flux<CardAcquirer> findByIsInternationalTrue();
    
    /**
     * Find domestic CardAcquirers.
     *
     * @return a Flux emitting all domestic CardAcquirers
     */
    Flux<CardAcquirer> findByIsDomesticTrue();
    
    /**
     * Find online CardAcquirers.
     *
     * @return a Flux emitting all online CardAcquirers
     */
    Flux<CardAcquirer> findByIsOnlineTrue();
    
    /**
     * Find physical CardAcquirers.
     *
     * @return a Flux emitting all physical CardAcquirers
     */
    Flux<CardAcquirer> findByIsPhysicalTrue();
    
    /**
     * Find mobile CardAcquirers.
     *
     * @return a Flux emitting all mobile CardAcquirers
     */
    Flux<CardAcquirer> findByIsMobileTrue();
    
    /**
     * Find CardAcquirers by processor ID.
     *
     * @param processorId the processor ID to search for
     * @return a Flux emitting the CardAcquirers for the specified processor
     */
    Flux<CardAcquirer> findByProcessorId(Long processorId);
    
    /**
     * Find CardAcquirers by gateway ID.
     *
     * @param gatewayId the gateway ID to search for
     * @return a Flux emitting the CardAcquirers for the specified gateway
     */
    Flux<CardAcquirer> findByGatewayId(Long gatewayId);
    
    /**
     * Find PCI compliant CardAcquirers.
     *
     * @return a Flux emitting all PCI compliant CardAcquirers
     */
    Flux<CardAcquirer> findByIsPciCompliantTrue();
}