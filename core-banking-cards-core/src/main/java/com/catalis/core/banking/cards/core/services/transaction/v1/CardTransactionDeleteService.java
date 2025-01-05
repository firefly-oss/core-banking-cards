package com.catalis.core.banking.cards.core.services.transaction.v1;

import com.catalis.core.banking.cards.models.repositories.transaction.v1.CardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardTransactionDeleteService {

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    /**
     * Deletes a card transaction by its unique identifier.
     *
     * @param transactionId the ID of the transaction to be deleted
     * @return a {@link Mono} that completes when the delete operation is finished
     */
    public Mono<Void> deleteTransaction(Long transactionId) {
        return cardTransactionRepository.deleteById(transactionId);
    }

}
