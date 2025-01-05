package com.catalis.core.banking.cards.core.services.virtual.v1;

import com.catalis.core.banking.cards.models.repositories.virtual.v1.VirtualCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VirtualCardDeleteService {

    @Autowired
    private VirtualCardRepository virtualCardRepository;

    /**
     * Deletes a virtual card from the repository based on the provided virtualCardId.
     *
     * @param virtualCardId the unique identifier of the virtual card to be deleted
     * @return a Mono that completes when the virtual card is successfully deleted or emits an error if the operation fails
     */
    public Mono<Void> deleteVirtualCard(Long virtualCardId) {
        return virtualCardRepository.deleteById(virtualCardId);
    }
}
