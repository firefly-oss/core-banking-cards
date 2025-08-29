package com.firefly.core.banking.cards.core.mappers.cardtype.v1;

import com.firefly.core.banking.cards.interfaces.dtos.cardtype.v1.CardTypeDTO;
import com.firefly.core.banking.cards.models.entities.cardtype.v1.CardType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardType entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardTypeMapper {

    /**
     * Convert a CardType entity to a CardTypeDTO.
     *
     * @param cardType the CardType entity to convert
     * @return the resulting CardTypeDTO
     */
    CardTypeDTO toDTO(CardType cardType);

    /**
     * Convert a CardTypeDTO to a CardType entity.
     *
     * @param cardTypeDTO the CardTypeDTO to convert
     * @return the resulting CardType entity
     */
    CardType toEntity(CardTypeDTO cardTypeDTO);
}
