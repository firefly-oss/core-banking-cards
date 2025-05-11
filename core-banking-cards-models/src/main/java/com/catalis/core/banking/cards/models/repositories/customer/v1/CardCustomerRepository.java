package com.catalis.core.banking.cards.models.repositories.customer.v1;

import com.catalis.core.banking.cards.models.entities.customer.v1.CardCustomer;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for managing CardCustomer entities.
 */
@Repository
public interface CardCustomerRepository extends BaseRepository<CardCustomer, Long> {
    /**
     * Find a CardCustomer by its ID.
     *
     * @param cardCustomerId the ID of the CardCustomer to find
     * @return a Mono emitting the CardCustomer if found, or empty if not found
     */
    Mono<CardCustomer> findByCardCustomerId(Long cardCustomerId);
    
    /**
     * Find a CardCustomer by customer ID.
     *
     * @param customerId the customer ID to search for
     * @return a Mono emitting the CardCustomer if found, or empty if not found
     */
    Mono<CardCustomer> findByCustomerId(Long customerId);
    
    /**
     * Find a CardCustomer by customer reference.
     *
     * @param customerReference the customer reference to search for
     * @return a Mono emitting the CardCustomer if found, or empty if not found
     */
    Mono<CardCustomer> findByCustomerReference(String customerReference);
    
    /**
     * Find CardCustomers by customer type.
     *
     * @param customerType the customer type to search for
     * @return a Flux emitting the CardCustomers of the specified type
     */
    Flux<CardCustomer> findByCustomerType(String customerType);
    
    /**
     * Find CardCustomers by customer status.
     *
     * @param customerStatus the customer status to search for
     * @return a Flux emitting the CardCustomers with the specified status
     */
    Flux<CardCustomer> findByCustomerStatus(String customerStatus);
    
    /**
     * Find CardCustomers by email.
     *
     * @param email the email to search for
     * @return a Flux emitting the CardCustomers with the specified email
     */
    Flux<CardCustomer> findByEmail(String email);
    
    /**
     * Find CardCustomers by phone.
     *
     * @param phone the phone to search for
     * @return a Flux emitting the CardCustomers with the specified phone
     */
    Flux<CardCustomer> findByPhone(String phone);
    
    /**
     * Find CardCustomers by ID type and number.
     *
     * @param idType the ID type to search for
     * @param idNumber the ID number to search for
     * @return a Flux emitting the CardCustomers with the specified ID type and number
     */
    Flux<CardCustomer> findByIdTypeAndIdNumber(String idType, String idNumber);
    
    /**
     * Find CardCustomers by country.
     *
     * @param country the country to search for
     * @return a Flux emitting the CardCustomers from the specified country
     */
    Flux<CardCustomer> findByCountry(String country);
    
    /**
     * Find CardCustomers by preferred language.
     *
     * @param preferredLanguage the preferred language to search for
     * @return a Flux emitting the CardCustomers with the specified preferred language
     */
    Flux<CardCustomer> findByPreferredLanguage(String preferredLanguage);
    
    /**
     * Find CardCustomers by preferred communication channel.
     *
     * @param preferredCommunicationChannel the preferred communication channel to search for
     * @return a Flux emitting the CardCustomers with the specified preferred communication channel
     */
    Flux<CardCustomer> findByPreferredCommunicationChannel(String preferredCommunicationChannel);
    
    /**
     * Find CardCustomers with marketing consent.
     *
     * @return a Flux emitting all CardCustomers with marketing consent
     */
    Flux<CardCustomer> findByIsMarketingConsentTrue();
    
    /**
     * Find CardCustomers who have accepted terms.
     *
     * @return a Flux emitting all CardCustomers who have accepted terms
     */
    Flux<CardCustomer> findByIsTermsAcceptedTrue();
    
    /**
     * Find CardCustomers by customer since date range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a Flux emitting the CardCustomers within the specified date range
     */
    Flux<CardCustomer> findByCustomerSinceBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Find CardCustomers by last activity date range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a Flux emitting the CardCustomers with last activity within the specified date range
     */
    Flux<CardCustomer> findByLastActivityDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Find VIP CardCustomers.
     *
     * @return a Flux emitting all VIP CardCustomers
     */
    Flux<CardCustomer> findByIsVipTrue();
    
    /**
     * Find CardCustomers by VIP tier.
     *
     * @param vipTier the VIP tier to search for
     * @return a Flux emitting the CardCustomers with the specified VIP tier
     */
    Flux<CardCustomer> findByVipTier(String vipTier);
    
    /**
     * Find CardCustomers by loyalty program ID.
     *
     * @param loyaltyProgramId the loyalty program ID to search for
     * @return a Flux emitting the CardCustomers enrolled in the specified loyalty program
     */
    Flux<CardCustomer> findByLoyaltyProgramId(Long loyaltyProgramId);
    
    /**
     * Find CardCustomers by loyalty program status.
     *
     * @param loyaltyProgramStatus the loyalty program status to search for
     * @return a Flux emitting the CardCustomers with the specified loyalty program status
     */
    Flux<CardCustomer> findByLoyaltyProgramStatus(String loyaltyProgramStatus);
}