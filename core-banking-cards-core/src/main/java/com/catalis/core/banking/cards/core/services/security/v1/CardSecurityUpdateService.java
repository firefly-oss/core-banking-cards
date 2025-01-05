package com.catalis.core.banking.cards.core.services.security.v1;

import com.catalis.core.banking.cards.core.mappers.security.v1.CardSecurityMapper;
import com.catalis.core.banking.cards.interfaces.dtos.security.v1.CardSecurityDTO;
import com.catalis.core.banking.cards.models.repositories.security.v1.CardSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardSecurityUpdateService {

    @Autowired
    private CardSecurityRepository cardSecurityRepository;

    @Autowired
    private CardSecurityMapper cardSecurityMapper;

    /**
     * Updates an existing card security record in the database.
     * Finds the card security record by its ID, updates its attributes with the provided DTO values,
     * saves the updated record, and maps it to a CardSecurityDTO.
     *
     * @param cardSecurityId the ID of the card security record to be updated
     * @param dto the CardSecurityDTO object containing updated values for the card security record
     * @return a Mono emitting the updated CardSecurityDTO object after successful update
     */
    public Mono<CardSecurityDTO> updateCardSecurity(Long cardSecurityId, CardSecurityDTO dto) {
        return cardSecurityRepository.findById(cardSecurityId)
                .flatMap(existing -> {
                    existing.setSecurityFeature(dto.getSecurityFeature());
                    existing.setSecurityStatus(dto.isSecurityStatus());
                    existing.setCardId(dto.getCardId());
                    return cardSecurityRepository.save(existing);
                })
                .map(cardSecurityMapper::toDTO);
    }

}
