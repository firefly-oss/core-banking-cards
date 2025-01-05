package com.catalis.core.banking.cards.core.services.security.v1;

import com.catalis.core.banking.cards.models.repositories.security.v1.CardSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardSecurityDeleteService {

    @Autowired
    private CardSecurityRepository cardSecurityRepository;

    /**
     * Deletes the card security record with the specified ID from the repository.
     *
     * @param cardSecurityId the ID of the card security record to be deleted
     * @return a Mono that completes when the deletion is successful
     */
    public Mono<Void> deleteCardSecurity(Long cardSecurityId) {
        return cardSecurityRepository.deleteById(cardSecurityId);
    }

}
