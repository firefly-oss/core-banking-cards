package com.catalis.core.banking.cards.core.services.configuration.v1;

import com.catalis.core.banking.cards.core.mappers.configuration.v1.CardConfigurationMapper;
import com.catalis.core.banking.cards.interfaces.dtos.configuration.v1.CardConfigurationDTO;
import com.catalis.core.banking.cards.models.repositories.configuration.v1.CardConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardConfigurationUpdateService {

    @Autowired
    private CardConfigurationRepository cardConfigurationRepository;

    @Autowired
    private CardConfigurationMapper cardConfigurationMapper;

    /**
     * Updates an existing card configuration identified by the provided cardConfigurationId
     * with the values in the given CardConfigurationDTO object.
     *
     * @param cardConfigurationId the ID of the card configuration to update
     * @param dto the CardConfigurationDTO containing the updated configuration details
     * @return a Mono emitting the updated CardConfigurationDTO object representing the modified card configuration
     */
    public Mono<CardConfigurationDTO> updateCardConfiguration(Long cardConfigurationId, CardConfigurationDTO dto) {
        return cardConfigurationRepository.findById(cardConfigurationId)
                .flatMap(existing -> {
                    existing.setConfigType(dto.getConfigType());
                    existing.setConfigValue(dto.isConfigValue());
                    existing.setCardId(dto.getCardId());
                    return cardConfigurationRepository.save(existing);
                })
                .map(cardConfigurationMapper::toDTO);
    }

}
