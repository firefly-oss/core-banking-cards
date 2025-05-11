package com.catalis.core.banking.cards.core.mappers.card.v1;

import com.catalis.core.banking.cards.interfaces.dtos.card.v1.CardDTO;
import com.catalis.core.banking.cards.models.entities.card.v1.Card;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the Card entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {

    /**
     * Convert a Card entity to a CardDTO.
     *
     * @param card the Card entity to convert
     * @return the resulting CardDTO
     */
    CardDTO toDTO(Card card);

    /**
     * Convert a CardDTO to a Card entity.
     *
     * @param cardDTO the CardDTO to convert
     * @return the resulting Card entity
     */
    Card toEntity(CardDTO cardDTO);
}
