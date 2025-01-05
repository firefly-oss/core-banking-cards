package com.catalis.core.banking.cards.core.services.configuration.v1;

import com.catalis.core.banking.cards.core.mappers.configuration.v1.CardConfigurationMapper;
import com.catalis.core.banking.cards.interfaces.dtos.configuration.v1.CardConfigurationDTO;
import com.catalis.core.banking.cards.models.entities.configuration.v1.CardConfiguration;
import com.catalis.core.banking.cards.models.repositories.configuration.v1.CardConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardConfigurationCreateService {

    @Autowired
    private CardConfigurationRepository cardConfigurationRepository;

    @Autowired
    private CardConfigurationMapper cardConfigurationMapper;

    /**
     * Creates and saves a new card configuration in the system.
     * Converts the provided CardConfigurationDTO into an entity, saves it in the repository,
     * and returns the saved entity as a DTO.
     *
     * @param dto the CardConfigurationDTO object containing the details of the card configuration to be created
     * @return a Mono emitting the saved CardConfigurationDTO object representing the created card configuration
     */
    public Mono<CardConfigurationDTO> createCardConfiguration(CardConfigurationDTO dto) {
        CardConfiguration entity = cardConfigurationMapper.toEntity(dto);
        return cardConfigurationRepository.save(entity)
                .map(cardConfigurationMapper::toDTO);
    }

}
