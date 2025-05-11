package com.catalis.core.banking.cards.core.mappers.acquirer.v1;

import com.catalis.core.banking.cards.interfaces.dtos.acquirer.v1.CardAcquirerDTO;
import com.catalis.core.banking.cards.models.entities.acquirer.v1.CardAcquirer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardAcquirer entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardAcquirerMapper {

    /**
     * Convert a CardAcquirer entity to a CardAcquirerDTO.
     *
     * @param cardAcquirer the CardAcquirer entity to convert
     * @return the resulting CardAcquirerDTO
     */
    CardAcquirerDTO toDTO(CardAcquirer cardAcquirer);

    /**
     * Convert a CardAcquirerDTO to a CardAcquirer entity.
     *
     * @param cardAcquirerDTO the CardAcquirerDTO to convert
     * @return the resulting CardAcquirer entity
     */
    CardAcquirer toEntity(CardAcquirerDTO cardAcquirerDTO);
}
