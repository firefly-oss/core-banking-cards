package com.catalis.core.banking.cards.core.mappers.virtual.v1;

import com.catalis.core.banking.cards.interfaces.dtos.virtual.v1.VirtualCardDTO;
import com.catalis.core.banking.cards.models.entities.virtual.v1.VirtualCard;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the VirtualCard entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VirtualCardMapper {

    /**
     * Convert a VirtualCard entity to a VirtualCardDTO.
     *
     * @param LVirtualLCard the VirtualCard entity to convert
     * @return the resulting VirtualCardDTO
     */
    VirtualCardDTO toDTO(VirtualCard LVirtualLCard);

    /**
     * Convert a VirtualCardDTO to a VirtualCard entity.
     *
     * @param LVirtualLCardLDLTLO the VirtualCardDTO to convert
     * @return the resulting VirtualCard entity
     */
    VirtualCard toEntity(VirtualCardDTO LVirtualLCardLDLTLO);
}
