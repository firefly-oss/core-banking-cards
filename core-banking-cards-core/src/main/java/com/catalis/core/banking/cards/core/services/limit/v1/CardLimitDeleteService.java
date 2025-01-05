package com.catalis.core.banking.cards.core.services.limit.v1;

import com.catalis.core.banking.cards.models.repositories.limit.v1.CardLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardLimitDeleteService {

    @Autowired
    private CardLimitRepository cardLimitRepository;

    /**
     * Deletes a card limit from the repository based on the given card limit ID.
     *
     * @param cardLimitId the ID of the card limit to be deleted
     * @return a Mono signaling when the deletion is complete
     */
    public Mono<Void> deleteCardLimit(Long cardLimitId) {
        return cardLimitRepository.deleteById(cardLimitId);
    }

}
