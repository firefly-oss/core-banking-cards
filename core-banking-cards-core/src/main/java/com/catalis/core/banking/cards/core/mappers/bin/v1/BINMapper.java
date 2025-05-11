package com.catalis.core.banking.cards.core.mappers.bin.v1;

import com.catalis.core.banking.cards.interfaces.dtos.bin.v1.BINDTO;
import com.catalis.core.banking.cards.models.entities.bin.v1.BIN;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the BIN entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BINMapper {

    /**
     * Convert a BIN entity to a BINDTO.
     *
     * @param bin the BIN entity to convert
     * @return the resulting BINDTO
     */
    BINDTO toDTO(BIN bin);

    /**
     * Convert a BINDTO to a BIN entity.
     *
     * @param bindto the BINDTO to convert
     * @return the resulting BIN entity
     */
    BIN toEntity(BINDTO bindto);
}