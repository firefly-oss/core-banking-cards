package com.catalis.core.banking.cards.core.mappers.security.v1;

import com.catalis.core.banking.cards.interfaces.dtos.security.v1.CardSecurityDTO;
import com.catalis.core.banking.cards.models.entities.security.v1.CardSecurity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardSecurityMapper {
    CardSecurityDTO toDTO (CardSecurity cardSecurity);
    CardSecurity toEntity (CardSecurityDTO cardSecurityDTO);
}
