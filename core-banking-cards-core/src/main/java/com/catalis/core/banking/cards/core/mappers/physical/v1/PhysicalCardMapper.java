package com.catalis.core.banking.cards.core.mappers.physical.v1;

import com.catalis.core.banking.cards.interfaces.dtos.physical.v1.PhysicalCardDTO;
import com.catalis.core.banking.cards.models.entities.physical.v1.PhysicalCard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhysicalCardMapper {
    PhysicalCardDTO toDTO (PhysicalCard physicalCard);
    PhysicalCard toEntity (PhysicalCardDTO physicalCardDTO);
}
