package com.catalis.core.banking.cards.core.services.limit.v1;

import com.catalis.core.banking.cards.core.mappers.limit.v1.CardLimitMapper;
import com.catalis.core.banking.cards.interfaces.dtos.limit.v1.CardLimitDTO;
import com.catalis.core.banking.cards.models.entities.limit.v1.CardLimit;
import com.catalis.core.banking.cards.models.repositories.limit.v1.CardLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardLimitCreateService {

    @Autowired
    private CardLimitRepository cardLimitRepository;

    @Autowired
    private CardLimitMapper cardLimitMapper;

    /**
     * Creates a new card limit based on the given CardLimitDTO and saves it to the repository.
     * The method converts the DTO to an entity, persists it, and then maps the saved entity back to a DTO.
     *
     * @param dto the CardLimitDTO containing the card limit details to be created
     * @return a Mono emitting the saved CardLimitDTO
     */
    public Mono<CardLimitDTO> createCardLimit(CardLimitDTO dto) {
        CardLimit entity = cardLimitMapper.toEntity(dto);
        return cardLimitRepository.save(entity)
                .map(cardLimitMapper::toDTO);
    }

}
