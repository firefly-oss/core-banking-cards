package com.catalis.core.banking.cards.core.mappers.transaction.v1;

import com.catalis.core.banking.cards.interfaces.dtos.transaction.v1.CardTransactionDTO;
import com.catalis.core.banking.cards.models.entities.transaction.v1.CardTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardTransactionMapper {
    CardTransactionDTO toDTO (CardTransaction cardTransaction);
    CardTransaction toEntity (CardTransactionDTO cardTransactionDTO);
}
