package com.firefly.core.banking.cards.core.mappers.application.v1;

import com.firefly.core.banking.cards.interfaces.dtos.application.v1.CardApplicationDTO;
import com.firefly.core.banking.cards.models.entities.application.v1.CardApplication;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardApplication entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardApplicationMapper {

    /**
     * Convert a CardApplication entity to a CardApplicationDTO.
     *
     * @param cardApplication the CardApplication entity to convert
     * @return the resulting CardApplicationDTO
     */
    CardApplicationDTO toDTO(CardApplication cardApplication);

    /**
     * Convert a CardApplicationDTO to a CardApplication entity.
     *
     * @param cardApplicationDTO the CardApplicationDTO to convert
     * @return the resulting CardApplication entity
     */
    CardApplication toEntity(CardApplicationDTO cardApplicationDTO);
}