package com.catalis.core.banking.cards.core.mappers.interest.v1;

import com.catalis.core.banking.cards.interfaces.dtos.interest.v1.CardInterestDTO;
import com.catalis.core.banking.cards.models.entities.interest.v1.CardInterest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardInterest entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardInterestMapper {

    /**
     * Convert a CardInterest entity to a CardInterestDTO.
     *
     * @param LCardLInterest the CardInterest entity to convert
     * @return the resulting CardInterestDTO
     */
    CardInterestDTO toDTO(CardInterest LCardLInterest);

    /**
     * Convert a CardInterestDTO to a CardInterest entity.
     *
     * @param LCardLInterestLDLTLO the CardInterestDTO to convert
     * @return the resulting CardInterest entity
     */
    CardInterest toEntity(CardInterestDTO LCardLInterestLDLTLO);
}
