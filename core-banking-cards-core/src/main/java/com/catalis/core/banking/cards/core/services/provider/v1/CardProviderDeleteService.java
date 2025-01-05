package com.catalis.core.banking.cards.core.services.provider.v1;

import com.catalis.core.banking.cards.models.repositories.provider.v1.CardProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardProviderDeleteService {

    @Autowired
    private CardProviderRepository cardProviderRepository;

    /**
     * Deletes a CardProvider entity with the specified ID from the system.
     *
     * @param cardProviderId the ID of the CardProvider to be deleted.
     * @return a Mono emitting completion signal when the CardProvider is deleted, or an error if the deletion fails.
     */
    public Mono<Void> deleteCardProvider(Long cardProviderId) {
        return cardProviderRepository.deleteById(cardProviderId);
    }

}
