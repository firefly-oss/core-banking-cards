package com.firefly.core.banking.cards.core.mappers.balance.v1;

import com.firefly.core.banking.cards.interfaces.dtos.balance.v1.CardBalanceDTO;
import com.firefly.core.banking.cards.models.entities.balance.v1.CardBalance;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardBalance entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardBalanceMapper {

    /**
     * Convert a CardBalance entity to a CardBalanceDTO.
     *
     * @param cardBalance the CardBalance entity to convert
     * @return the resulting CardBalanceDTO
     */
    CardBalanceDTO toDTO(CardBalance cardBalance);

    /**
     * Convert a CardBalanceDTO to a CardBalance entity.
     *
     * @param cardBalanceDTO the CardBalanceDTO to convert
     * @return the resulting CardBalance entity
     */
    CardBalance toEntity(CardBalanceDTO cardBalanceDTO);
}