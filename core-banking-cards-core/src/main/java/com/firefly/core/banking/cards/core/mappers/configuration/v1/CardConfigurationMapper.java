package com.firefly.core.banking.cards.core.mappers.configuration.v1;

import com.firefly.core.banking.cards.interfaces.dtos.configuration.v1.CardConfigurationDTO;
import com.firefly.core.banking.cards.models.entities.configuration.v1.CardConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardConfiguration entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardConfigurationMapper {

    /**
     * Convert a CardConfiguration entity to a CardConfigurationDTO.
     *
     * @param cardConfiguration the CardConfiguration entity to convert
     * @return the resulting CardConfigurationDTO
     */
    CardConfigurationDTO toDTO(CardConfiguration cardConfiguration);

    /**
     * Convert a CardConfigurationDTO to a CardConfiguration entity.
     *
     * @param cardConfigurationDTO the CardConfigurationDTO to convert
     * @return the resulting CardConfiguration entity
     */
    CardConfiguration toEntity(CardConfigurationDTO cardConfigurationDTO);
}