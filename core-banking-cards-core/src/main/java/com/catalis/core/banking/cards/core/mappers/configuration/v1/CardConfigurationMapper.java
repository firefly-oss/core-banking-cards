package com.catalis.core.banking.cards.core.mappers.configuration.v1;

import com.catalis.core.banking.cards.interfaces.dtos.configuration.v1.CardConfigurationDTO;
import com.catalis.core.banking.cards.models.entities.configuration.v1.CardConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardConfigurationMapper {
    CardConfigurationDTO toDTO (CardConfiguration cardConfiguration);
    CardConfiguration toEntity (CardConfigurationDTO cardConfigurationDTO);
}
