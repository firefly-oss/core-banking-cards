package com.firefly.core.banking.cards.core.mappers.design.v1;

import com.firefly.core.banking.cards.interfaces.dtos.design.v1.CardDesignDTO;
import com.firefly.core.banking.cards.models.entities.design.v1.CardDesign;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardDesign entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardDesignMapper {

    /**
     * Convert a CardDesign entity to a CardDesignDTO.
     *
     * @param cardDesign the CardDesign entity to convert
     * @return the resulting CardDesignDTO
     */
    CardDesignDTO toDTO(CardDesign cardDesign);

    /**
     * Convert a CardDesignDTO to a CardDesign entity.
     *
     * @param cardDesignDTO the CardDesignDTO to convert
     * @return the resulting CardDesign entity
     */
    CardDesign toEntity(CardDesignDTO cardDesignDTO);
}
