package com.firefly.core.banking.cards.models.repositories.program.v1;

import com.firefly.core.banking.cards.models.entities.program.v1.CardProgram;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Repository for managing CardProgram entities.
 */
@Repository
public interface CardProgramRepository extends BaseRepository<CardProgram, UUID> {
    /**
     * Find a CardProgram by its ID.
     *
     * @param programId the ID of the CardProgram to find
     * @return a Mono emitting the CardProgram if found, or empty if not found
     */
    Mono<CardProgram> findByProgramId(UUID programId);
    
    /**
     * Find a CardProgram by its code.
     *
     * @param programCode the code of the CardProgram to find
     * @return a Mono emitting the CardProgram if found, or empty if not found
     */
    Mono<CardProgram> findByProgramCode(String programCode);
    
    /**
     * Find CardPrograms by issuer ID.
     *
     * @param issuerId the issuer ID to search for
     * @return a Flux emitting the CardPrograms for the specified issuer
     */
    Flux<CardProgram> findByIssuerId(UUID issuerId);
    
    /**
     * Find CardPrograms by BIN ID.
     *
     * @param binId the BIN ID to search for
     * @return a Flux emitting the CardPrograms for the specified BIN
     */
    Flux<CardProgram> findByBinId(UUID binId);
    
    /**
     * Find CardPrograms by card type ID.
     *
     * @param cardTypeId the card type ID to search for
     * @return a Flux emitting the CardPrograms for the specified card type
     */
    Flux<CardProgram> findByCardTypeId(UUID cardTypeId);
    
    /**
     * Find CardPrograms by card network ID.
     *
     * @param cardNetworkId the card network ID to search for
     * @return a Flux emitting the CardPrograms for the specified card network
     */
    Flux<CardProgram> findByCardNetworkId(UUID cardNetworkId);
    
    /**
     * Find active CardPrograms.
     *
     * @return a Flux emitting all active CardPrograms
     */
    Flux<CardProgram> findByIsActiveTrue();
    
    /**
     * Find CardPrograms by currency code.
     *
     * @param currencyCode the currency code to search for
     * @return a Flux emitting the CardPrograms for the specified currency
     */
    Flux<CardProgram> findByCurrencyCode(String currencyCode);
    
    /**
     * Find CardPrograms by country code.
     *
     * @param countryCode the country code to search for
     * @return a Flux emitting the CardPrograms for the specified country
     */
    Flux<CardProgram> findByCountryCode(String countryCode);
    
    /**
     * Find active CardPrograms that are currently valid (between start and end dates).
     *
     * @param currentDate the current date to check against
     * @return a Flux emitting all active and valid CardPrograms
     */
    Flux<CardProgram> findByIsActiveTrueAndStartDateBeforeAndEndDateAfter(
            LocalDateTime currentDate, LocalDateTime currentDateEnd);
}