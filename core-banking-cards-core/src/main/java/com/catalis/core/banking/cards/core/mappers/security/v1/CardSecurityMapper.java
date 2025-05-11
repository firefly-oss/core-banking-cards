package com.catalis.core.banking.cards.core.mappers.security.v1;

import com.catalis.core.banking.cards.interfaces.dtos.security.v1.CardSecurityDTO;
import com.catalis.core.banking.cards.interfaces.enums.security.v1.SecurityFeatureEnum;
import com.catalis.core.banking.cards.models.entities.security.v1.CardSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Named;

/**
 * Mapper for the CardSecurity entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardSecurityMapper {

    /**
     * Convert a CardSecurity entity to a CardSecurityDTO.
     *
     * @param LCardLSecurity the CardSecurity entity to convert
     * @return the resulting CardSecurityDTO
     */
    CardSecurityDTO toDTO(CardSecurity LCardLSecurity);

    /**
     * Convert a CardSecurityDTO to a CardSecurity entity.
     *
     * @param LCardLSecurityLDLTLO the CardSecurityDTO to convert
     * @return the resulting CardSecurity entity
     */
    CardSecurity toEntity(CardSecurityDTO LCardLSecurityLDLTLO);
}
