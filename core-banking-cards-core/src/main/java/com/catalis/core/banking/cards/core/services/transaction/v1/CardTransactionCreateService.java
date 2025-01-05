package com.catalis.core.banking.cards.core.services.transaction.v1;

import com.catalis.core.banking.cards.core.mappers.transaction.v1.CardTransactionMapper;
import com.catalis.core.banking.cards.interfaces.dtos.transaction.v1.CardTransactionDTO;
import com.catalis.core.banking.cards.models.entities.transaction.v1.CardTransaction;
import com.catalis.core.banking.cards.models.repositories.transaction.v1.CardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardTransactionCreateService {

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @Autowired
    private CardTransactionMapper cardTransactionMapper;

    /**
     * Creates a new card transaction record based on the provided CardTransactionDTO.
     * Converts the DTO to an entity, persists it in the database, and then maps the saved entity back to a DTO.
     *
     * @param dto the {@link CardTransactionDTO} containing transaction details to be created
     * @return a {@link Mono} emitting the saved {@link CardTransactionDTO} after successful persistence
     */
    public Mono<CardTransactionDTO> createTransaction(CardTransactionDTO dto) {
        CardTransaction entity = cardTransactionMapper.toEntity(dto);
        return cardTransactionRepository.save(entity)
                .map(cardTransactionMapper::toDTO);
    }

}
