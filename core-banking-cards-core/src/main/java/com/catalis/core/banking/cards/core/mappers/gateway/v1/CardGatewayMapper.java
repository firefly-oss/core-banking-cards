package com.catalis.core.banking.cards.core.mappers.gateway.v1;

import com.catalis.core.banking.cards.interfaces.dtos.gateway.v1.CardGatewayDTO;
import com.catalis.core.banking.cards.models.entities.gateway.v1.CardGateway;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardGateway entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardGatewayMapper {

    /**
     * Convert a CardGateway entity to a CardGatewayDTO.
     *
     * @param cardGateway the CardGateway entity to convert
     * @return the resulting CardGatewayDTO
     */
    CardGatewayDTO toDTO(CardGateway cardGateway);

    /**
     * Convert a CardGatewayDTO to a CardGateway entity.
     *
     * @param cardGatewayDTO the CardGatewayDTO to convert
     * @return the resulting CardGateway entity
     */
    CardGateway toEntity(CardGatewayDTO cardGatewayDTO);
}
