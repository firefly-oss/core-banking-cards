package com.catalis.core.banking.cards.core.mappers.card.v1;

import com.catalis.core.banking.cards.interfaces.dtos.card.v1.CardDTO;
import com.catalis.core.banking.cards.models.entities.card.v1.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardDTO toDTO (Card card);
    Card toEntity (CardDTO cardDTO);
}