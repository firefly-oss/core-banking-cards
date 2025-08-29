package com.firefly.core.banking.cards.core.mappers.provider.v1;

import com.firefly.core.banking.cards.interfaces.dtos.provider.v1.CardProviderDTO;
import com.firefly.core.banking.cards.models.entities.provider.v1.CardProvider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardProviderMapper {
    CardProviderDTO toDTO (CardProvider cardProvider);
    CardProvider toEntity (CardProviderDTO cardProviderDTO);
}
