package com.catalis.core.banking.cards.core.mappers.virtual.v1;

import com.catalis.core.banking.cards.interfaces.dtos.virtual.v1.VirtualCardDTO;
import com.catalis.core.banking.cards.models.entities.virtual.v1.VirtualCard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VirtualCardMapper {
    VirtualCardDTO toDTO (VirtualCard virtualCard);
    VirtualCard toEntity (VirtualCardDTO virtualCardDTO);
}