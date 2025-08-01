package com.catalis.core.banking.cards.models.repositories.gateway.v1;

import com.catalis.core.banking.cards.models.entities.gateway.v1.CardGateway;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for managing CardGateway entities.
 */
@Repository
public interface CardGatewayRepository extends BaseRepository<CardGateway, Long> {
    /**
     * Find a CardGateway by its ID.
     *
     * @param gatewayId the ID of the CardGateway to find
     * @return a Mono emitting the CardGateway if found, or empty if not found
     */
    Mono<CardGateway> findByGatewayId(Long gatewayId);
    
    /**
     * Find a CardGateway by its reference.
     *
     * @param gatewayReference the reference of the CardGateway to find
     * @return a Mono emitting the CardGateway if found, or empty if not found
     */
    Mono<CardGateway> findByGatewayReference(String gatewayReference);
    
    /**
     * Find CardGateways by gateway name.
     *
     * @param gatewayName the gateway name to search for
     * @return a Flux emitting the CardGateways with the specified name
     */
    Flux<CardGateway> findByGatewayName(String gatewayName);
    
    /**
     * Find CardGateways by gateway legal name.
     *
     * @param gatewayLegalName the gateway legal name to search for
     * @return a Flux emitting the CardGateways with the specified legal name
     */
    Flux<CardGateway> findByGatewayLegalName(String gatewayLegalName);
    
    /**
     * Find CardGateways by gateway code.
     *
     * @param gatewayCode the gateway code to search for
     * @return a Flux emitting the CardGateways with the specified code
     */
    Flux<CardGateway> findByGatewayCode(String gatewayCode);
    
    /**
     * Find CardGateways by gateway type.
     *
     * @param gatewayType the gateway type to search for
     * @return a Flux emitting the CardGateways of the specified type
     */
    Flux<CardGateway> findByGatewayType(String gatewayType);
    
    /**
     * Find CardGateways by gateway status.
     *
     * @param gatewayStatus the gateway status to search for
     * @return a Flux emitting the CardGateways with the specified status
     */
    Flux<CardGateway> findByGatewayStatus(String gatewayStatus);
    
    /**
     * Find active CardGateways.
     *
     * @return a Flux emitting all active CardGateways
     */
    Flux<CardGateway> findByIsActiveTrue();
    
    /**
     * Find CardGateways by country.
     *
     * @param country the country to search for
     * @return a Flux emitting the CardGateways from the specified country
     */
    Flux<CardGateway> findByCountry(String country);
    
    /**
     * Find CardGateways by supported card networks.
     *
     * @param cardNetwork the card network to search for
     * @return a Flux emitting the CardGateways supporting the specified card network
     */
    Flux<CardGateway> findBySupportedCardNetworksContaining(String cardNetwork);
    
    /**
     * Find CardGateways by supported payment methods.
     *
     * @param paymentMethod the payment method to search for
     * @return a Flux emitting the CardGateways supporting the specified payment method
     */
    Flux<CardGateway> findBySupportedPaymentMethodsContaining(String paymentMethod);
    
    /**
     * Find CardGateways by supported currencies.
     *
     * @param currencyCode the currency code to search for
     * @return a Flux emitting the CardGateways supporting the specified currency
     */
    Flux<CardGateway> findBySupportedCurrenciesContaining(String currencyCode);
    
    /**
     * Find CardGateways by supported countries.
     *
     * @param countryCode the country code to search for
     * @return a Flux emitting the CardGateways supporting the specified country
     */
    Flux<CardGateway> findBySupportedCountriesContaining(String countryCode);
    
    /**
     * Find international CardGateways.
     *
     * @return a Flux emitting all international CardGateways
     */
    Flux<CardGateway> findByIsInternationalTrue();
    
    /**
     * Find domestic CardGateways.
     *
     * @return a Flux emitting all domestic CardGateways
     */
    Flux<CardGateway> findByIsDomesticTrue();
    
    /**
     * Find CardGateways by processor ID.
     *
     * @param processorId the processor ID to search for
     * @return a Flux emitting the CardGateways for the specified processor
     */
    Flux<CardGateway> findByProcessorId(Long processorId);
    
    /**
     * Find CardGateways by acquirer ID.
     *
     * @param acquirerId the acquirer ID to search for
     * @return a Flux emitting the CardGateways for the specified acquirer
     */
    Flux<CardGateway> findByAcquirerId(Long acquirerId);
    
    /**
     * Find PCI compliant CardGateways.
     *
     * @return a Flux emitting all PCI compliant CardGateways
     */
    Flux<CardGateway> findByIsPciCompliantTrue();
    
    /**
     * Find CardGateways that support tokenization.
     *
     * @return a Flux emitting all CardGateways that support tokenization
     */
    Flux<CardGateway> findBySupportsTokenizationTrue();
    
    /**
     * Find CardGateways that support 3D Secure.
     *
     * @return a Flux emitting all CardGateways that support 3D Secure
     */
    Flux<CardGateway> findBySupports3dSecureTrue();
    
    /**
     * Find CardGateways that support recurring payments.
     *
     * @return a Flux emitting all CardGateways that support recurring payments
     */
    Flux<CardGateway> findBySupportsRecurringPaymentsTrue();
    
    /**
     * Find CardGateways by integration type.
     *
     * @param integrationType the integration type to search for
     * @return a Flux emitting the CardGateways with the specified integration type
     */
    Flux<CardGateway> findByIntegrationType(String integrationType);
    
    /**
     * Find CardGateways with SDK available.
     *
     * @return a Flux emitting all CardGateways with SDK available
     */
    Flux<CardGateway> findBySdkAvailableTrue();
}