package com.catalis.core.banking.cards.models.repositories.bin.v1;

import com.catalis.core.banking.cards.models.entities.bin.v1.BIN;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Repository for managing BIN (Bank Identification Number) entities.
 */
@Repository
public interface BINRepository extends BaseRepository<BIN, Long> {
    /**
     * Find a BIN by its ID.
     *
     * @param binId the ID of the BIN to find
     * @return a Mono emitting the BIN if found, or empty if not found
     */
    Mono<BIN> findByBinId(Long binId);
    
    /**
     * Find a BIN by its number.
     *
     * @param binNumber the number of the BIN to find
     * @return a Mono emitting the BIN if found, or empty if not found
     */
    Mono<BIN> findByBinNumber(String binNumber);
}