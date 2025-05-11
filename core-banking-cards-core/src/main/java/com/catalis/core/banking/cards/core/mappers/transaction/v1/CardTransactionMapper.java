package com.catalis.core.banking.cards.core.mappers.transaction.v1;

import com.catalis.core.banking.cards.interfaces.dtos.transaction.v1.CardTransactionDTO;
import com.catalis.core.banking.cards.models.entities.transaction.v1.CardTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardTransaction entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardTransactionMapper {

    /**
     * Convert a CardTransaction entity to a CardTransactionDTO.
     *
     * @param LCardLTransaction the CardTransaction entity to convert
     * @return the resulting CardTransactionDTO
     */
    CardTransactionDTO toDTO(CardTransaction LCardLTransaction);

    /**
     * Convert a CardTransactionDTO to a CardTransaction entity.
     *
     * @param LCardLTransactionLDLTLO the CardTransactionDTO to convert
     * @return the resulting CardTransaction entity
     */
    CardTransaction toEntity(CardTransactionDTO LCardLTransactionLDLTLO);
}
