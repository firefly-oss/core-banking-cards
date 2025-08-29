package com.firefly.core.banking.cards.core.mappers.statement.v1;

import com.firefly.core.banking.cards.interfaces.dtos.statement.v1.CardStatementDTO;
import com.firefly.core.banking.cards.models.entities.statement.v1.CardStatement;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardStatement entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardStatementMapper {

    /**
     * Convert a CardStatement entity to a CardStatementDTO.
     *
     * @param LCardLStatement the CardStatement entity to convert
     * @return the resulting CardStatementDTO
     */
    CardStatementDTO toDTO(CardStatement LCardLStatement);

    /**
     * Convert a CardStatementDTO to a CardStatement entity.
     *
     * @param LCardLStatementLDLTLO the CardStatementDTO to convert
     * @return the resulting CardStatement entity
     */
    CardStatement toEntity(CardStatementDTO LCardLStatementLDLTLO);
}
