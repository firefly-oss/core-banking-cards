package com.catalis.core.banking.cards.core.services.physical.v1;

import com.catalis.core.banking.cards.models.repositories.physical.v1.PhysicalCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class PhysicalCardDeleteService {

    @Autowired
    private PhysicalCardRepository physicalCardRepository;

    /**
     * Deletes a physical card identified by the given ID.
     *
     * @param physicalCardId the ID of the physical card to be deleted
     * @return a Mono representing the asynchronous completion of the delete operation
     */
    public Mono<Void> deletePhysicalCard(Long physicalCardId) {
        return physicalCardRepository.deleteById(physicalCardId);
    }
}
