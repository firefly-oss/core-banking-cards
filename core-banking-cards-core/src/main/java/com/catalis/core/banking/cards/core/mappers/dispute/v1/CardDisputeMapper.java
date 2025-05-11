package com.catalis.core.banking.cards.core.mappers.dispute.v1;

import com.catalis.core.banking.cards.interfaces.dtos.dispute.v1.CardDisputeDTO;
import com.catalis.core.banking.cards.models.entities.dispute.v1.CardDispute;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardDispute entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardDisputeMapper {

    /**
     * Convert a CardDispute entity to a CardDisputeDTO.
     *
     * @param LCardLDispute the CardDispute entity to convert
     * @return the resulting CardDisputeDTO
     */
    CardDisputeDTO toDTO(CardDispute LCardLDispute);

    /**
     * Convert a CardDisputeDTO to a CardDispute entity.
     *
     * @param LCardLDisputeLDLTLO the CardDisputeDTO to convert
     * @return the resulting CardDispute entity
     */
    CardDispute toEntity(CardDisputeDTO LCardLDisputeLDLTLO);
}
