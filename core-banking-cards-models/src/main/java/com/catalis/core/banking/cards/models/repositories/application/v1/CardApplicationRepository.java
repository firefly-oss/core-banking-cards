package com.catalis.core.banking.cards.models.repositories.application.v1;

import com.catalis.core.banking.cards.models.entities.application.v1.CardApplication;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for managing CardApplication entities.
 */
@Repository
public interface CardApplicationRepository extends BaseRepository<CardApplication, Long> {
    /**
     * Find a CardApplication by its ID.
     *
     * @param applicationId the ID of the CardApplication to find
     * @return a Mono emitting the CardApplication if found, or empty if not found
     */
    Mono<CardApplication> findByApplicationId(Long applicationId);

    /**
     * Find a CardApplication by its reference.
     *
     * @param applicationReference the reference of the CardApplication to find
     * @return a Mono emitting the CardApplication if found, or empty if not found
     */
    Mono<CardApplication> findByApplicationReference(String applicationReference);

    /**
     * Find CardApplications by party ID.
     *
     * @param partyId the party ID to search for
     * @return a Flux emitting the CardApplications for the specified party
     */
    Flux<CardApplication> findByPartyId(Long partyId);

    /**
     * Find CardApplications by account ID.
     *
     * @param accountId the account ID to search for
     * @return a Flux emitting the CardApplications for the specified account
     */
    Flux<CardApplication> findByAccountId(Long accountId);

    /**
     * Find CardApplications by application type.
     *
     * @param applicationType the application type to search for
     * @return a Flux emitting the CardApplications of the specified type
     */
    Flux<CardApplication> findByApplicationType(String applicationType);

    /**
     * Find CardApplications by card type ID.
     *
     * @param cardTypeId the card type ID to search for
     * @return a Flux emitting the CardApplications for the specified card type
     */
    Flux<CardApplication> findByCardTypeId(Long cardTypeId);

    /**
     * Find CardApplications by program ID.
     *
     * @param programId the program ID to search for
     * @return a Flux emitting the CardApplications for the specified program
     */
    Flux<CardApplication> findByProgramId(Long programId);

    /**
     * Find CardApplications by design ID.
     *
     * @param designId the design ID to search for
     * @return a Flux emitting the CardApplications for the specified design
     */
    Flux<CardApplication> findByDesignId(Long designId);

    /**
     * Find CardApplications by application status.
     *
     * @param applicationStatus the application status to search for
     * @return a Flux emitting the CardApplications with the specified status
     */
    Flux<CardApplication> findByApplicationStatus(String applicationStatus);

    /**
     * Find CardApplications by application stage.
     *
     * @param applicationStage the application stage to search for
     * @return a Flux emitting the CardApplications at the specified stage
     */
    Flux<CardApplication> findByApplicationStage(String applicationStage);

    /**
     * Find CardApplications by application channel.
     *
     * @param applicationChannel the application channel to search for
     * @return a Flux emitting the CardApplications from the specified channel
     */
    Flux<CardApplication> findByApplicationChannel(String applicationChannel);

    /**
     * Find CardApplications by application timestamp range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a Flux emitting the CardApplications within the specified date range
     */
    Flux<CardApplication> findByApplicationTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find pre-approved CardApplications.
     *
     * @return a Flux emitting all pre-approved CardApplications
     */
    Flux<CardApplication> findByIsPreApprovedTrue();

    /**
     * Find instant issuance CardApplications.
     *
     * @return a Flux emitting all instant issuance CardApplications
     */
    Flux<CardApplication> findByIsInstantIssuanceTrue();

    /**
     * Find digital-only CardApplications.
     *
     * @return a Flux emitting all digital-only CardApplications
     */
    Flux<CardApplication> findByIsDigitalOnlyTrue();

    /**
     * Find CardApplications requiring physical cards.
     *
     * @return a Flux emitting all CardApplications requiring physical cards
     */
    Flux<CardApplication> findByRequiresPhysicalCardTrue();

    /**
     * Find CardApplications by KYC status.
     *
     * @param kycStatus the KYC status to search for
     * @return a Flux emitting the CardApplications with the specified KYC status
     */
    Flux<CardApplication> findByKycStatus(String kycStatus);

    /**
     * Find CardApplications by AML status.
     *
     * @param amlStatus the AML status to search for
     * @return a Flux emitting the CardApplications with the specified AML status
     */
    Flux<CardApplication> findByAmlStatus(String amlStatus);

    /**
     * Find CardApplications by card ID.
     *
     * @param cardId the card ID to search for
     * @return a Flux emitting the CardApplications for the specified card
     */
    Flux<CardApplication> findByCardId(Long cardId);

    /**
     * Find CardApplications with accepted terms.
     *
     * @return a Flux emitting all CardApplications with accepted terms
     */
    Flux<CardApplication> findByTermsAcceptedTrue();
}