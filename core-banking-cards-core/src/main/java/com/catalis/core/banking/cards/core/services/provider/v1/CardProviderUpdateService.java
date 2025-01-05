package com.catalis.core.banking.cards.core.services.provider.v1;

import com.catalis.core.banking.cards.core.mappers.provider.v1.CardProviderMapper;
import com.catalis.core.banking.cards.interfaces.dtos.provider.v1.CardProviderDTO;
import com.catalis.core.banking.cards.models.repositories.provider.v1.CardProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardProviderUpdateService {

    @Autowired
    private CardProviderRepository cardProviderRepository;

    @Autowired
    private CardProviderMapper cardProviderMapper;

    /**
     * Updates an existing CardProvider entry in the database based on the specified ID and provided DTO.
     * The existing entity is fetched and its fields are updated using the information from the given DTO.
     * The updated entity is then saved in the database and returned in DTO format.
     *
     * @param cardProviderId the ID of the CardProvider to be updated.
     * @param dto the CardProviderDTO object containing the updated information.
     * @return a Mono emitting the updated CardProviderDTO object after persistence.
     */
    public Mono<CardProviderDTO> updateCardProvider(Long cardProviderId, CardProviderDTO dto) {
        return cardProviderRepository.findById(cardProviderId)
                .flatMap(existing -> {
                    existing.setProviderName(dto.getProviderName());
                    existing.setExternalReference(dto.getExternalReference());
                    existing.setStatus(dto.getStatus());
                    existing.setCardId(dto.getCardId());
                    return cardProviderRepository.save(existing);
                })
                .map(cardProviderMapper::toDTO);
    }
    
}
