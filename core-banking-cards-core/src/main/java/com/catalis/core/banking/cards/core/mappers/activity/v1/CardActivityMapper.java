package com.catalis.core.banking.cards.core.mappers.activity.v1;

import com.catalis.core.banking.cards.interfaces.dtos.activity.v1.CardActivityDTO;
import com.catalis.core.banking.cards.models.entities.activity.v1.CardActivity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardActivity entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardActivityMapper {

    /**
     * Convert a CardActivity entity to a CardActivityDTO.
     *
     * @param cardActivity the CardActivity entity to convert
     * @return the resulting CardActivityDTO
     */
    CardActivityDTO toDTO(CardActivity cardActivity);

    /**
     * Convert a CardActivityDTO to a CardActivity entity.
     *
     * @param cardActivityDTO the CardActivityDTO to convert
     * @return the resulting CardActivity entity
     */
    CardActivity toEntity(CardActivityDTO cardActivityDTO);
}
