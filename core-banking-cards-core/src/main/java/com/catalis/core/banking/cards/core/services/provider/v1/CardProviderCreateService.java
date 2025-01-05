package com.catalis.core.banking.cards.core.services.provider.v1;

import com.catalis.core.banking.cards.core.mappers.provider.v1.CardProviderMapper;
import com.catalis.core.banking.cards.interfaces.dtos.provider.v1.CardProviderDTO;
import com.catalis.core.banking.cards.models.entities.provider.v1.CardProvider;
import com.catalis.core.banking.cards.models.repositories.provider.v1.CardProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardProviderCreateService {

    @Autowired
    private CardProviderRepository cardProviderRepository;

    @Autowired
    private CardProviderMapper cardProviderMapper;

    /**
     * Creates a new CardProvider entry in the database based on the provided DTO and returns the created entity in DTO format.
     *
     * @param dto the CardProviderDTO object containing the information for the new CardProvider.
     * @return a Mono emitting the created CardProviderDTO object after persistence.
     */
    public Mono<CardProviderDTO> createCardProvider(CardProviderDTO dto) {
        CardProvider entity = cardProviderMapper.toEntity(dto);
        return cardProviderRepository.save(entity)
                .map(cardProviderMapper::toDTO);
    }

}