package com.firefly.core.banking.cards.core.mappers.network.v1;

import com.firefly.core.banking.cards.interfaces.dtos.network.v1.CardNetworkDTO;
import com.firefly.core.banking.cards.models.entities.network.v1.CardNetwork;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardNetwork entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardNetworkMapper {

    /**
     * Convert a CardNetwork entity to a CardNetworkDTO.
     *
     * @param LCardLNetwork the CardNetwork entity to convert
     * @return the resulting CardNetworkDTO
     */
    CardNetworkDTO toDTO(CardNetwork LCardLNetwork);

    /**
     * Convert a CardNetworkDTO to a CardNetwork entity.
     *
     * @param LCardLNetworkLDLTLO the CardNetworkDTO to convert
     * @return the resulting CardNetwork entity
     */
    CardNetwork toEntity(CardNetworkDTO LCardLNetworkLDLTLO);
}
