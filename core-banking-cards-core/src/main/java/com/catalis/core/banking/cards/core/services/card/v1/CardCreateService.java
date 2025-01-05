package com.catalis.core.banking.cards.core.services.card.v1;

import com.catalis.core.banking.cards.interfaces.dtos.card.v1.CardDTO;
import com.catalis.core.banking.cards.models.entities.card.v1.Card;
import com.catalis.core.banking.cards.models.repositories.card.v1.CardRepository;
import com.catalis.core.banking.cards.core.mappers.card.v1.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardCreateService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;

    /**
     * Creates a new card and saves it to the repository.
     * The input DTO is mapped to an entity before being persisted.
     * After the entity is saved, it is mapped back to a DTO and returned.
     *
     * @param cardDTO The CardDTO containing the details of the card to be created.
     * @return A Mono of CardDTO representing the successfully created card.
     */
    public Mono<CardDTO> createCard(CardDTO cardDTO) {
        Card entity = cardMapper.toEntity(cardDTO);
        return cardRepository.save(entity)
                .map(cardMapper::toDTO);
    }
}
