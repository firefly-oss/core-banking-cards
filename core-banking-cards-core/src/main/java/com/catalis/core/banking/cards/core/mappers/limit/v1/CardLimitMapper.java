package com.catalis.core.banking.cards.core.mappers.limit.v1;

import com.catalis.core.banking.cards.interfaces.dtos.limit.v1.CardLimitDTO;
import com.catalis.core.banking.cards.models.entities.limit.v1.CardLimit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardLimitMapper {
    CardLimitDTO toDTO (CardLimit cardLimit);
    CardLimit toEntity (CardLimitDTO cardLimitDTO);
}
