package com.catalis.core.banking.cards.core.mappers.fee.v1;

import com.catalis.core.banking.cards.interfaces.dtos.fee.v1.CardFeeDTO;
import com.catalis.core.banking.cards.models.entities.fee.v1.CardFee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardFee entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardFeeMapper {

    /**
     * Convert a CardFee entity to a CardFeeDTO.
     *
     * @param LCardLFee the CardFee entity to convert
     * @return the resulting CardFeeDTO
     */
    CardFeeDTO toDTO(CardFee LCardLFee);

    /**
     * Convert a CardFeeDTO to a CardFee entity.
     *
     * @param LCardLFeeLDLTLO the CardFeeDTO to convert
     * @return the resulting CardFee entity
     */
    CardFee toEntity(CardFeeDTO LCardLFeeLDLTLO);
}
