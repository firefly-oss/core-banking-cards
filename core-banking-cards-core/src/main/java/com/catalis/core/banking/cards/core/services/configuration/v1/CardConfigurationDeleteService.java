package com.catalis.core.banking.cards.core.services.configuration.v1;

import com.catalis.core.banking.cards.models.repositories.configuration.v1.CardConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardConfigurationDeleteService {

    @Autowired
    private CardConfigurationRepository cardConfigurationRepository;

    /**
     * Deletes a card configuration identified by the provided cardConfigurationId.
     * This method removes the record from the repository if it exists.
     *
     * @param cardConfigurationId the ID of the card configuration to delete
     * @return a Mono signaling completion of the deletion process
     */
    public Mono<Void> deleteCardConfiguration(Long cardConfigurationId) {
        return cardConfigurationRepository.deleteById(cardConfigurationId);
    }

}
