package com.catalis.core.banking.cards.core.services.security.v1;

import com.catalis.core.banking.cards.core.mappers.security.v1.CardSecurityMapper;
import com.catalis.core.banking.cards.interfaces.dtos.security.v1.CardSecurityDTO;
import com.catalis.core.banking.cards.models.entities.security.v1.CardSecurity;
import com.catalis.core.banking.cards.models.repositories.security.v1.CardSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardSecurityCreateService {

    @Autowired
    private CardSecurityRepository cardSecurityRepository;

    @Autowired
    private CardSecurityMapper cardSecurityMapper;

    /**
     * Creates a new card security record in the database based on the provided CardSecurityDTO object.
     * Maps the CardSecurityDTO object to a CardSecurity entity, saves it, and maps the saved entity back to a DTO.
     *
     * @param dto the CardSecurityDTO object containing the details of the card security to be created
     * @return a Mono emitting the saved CardSecurityDTO object after successful creation
     */
    public Mono<CardSecurityDTO> createCardSecurity(CardSecurityDTO dto) {
        CardSecurity entity = cardSecurityMapper.toEntity(dto);
        return cardSecurityRepository.save(entity)
                .map(cardSecurityMapper::toDTO);
    }

}
