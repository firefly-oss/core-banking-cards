package com.catalis.core.banking.cards.core.mappers.issuer.v1;

import com.catalis.core.banking.cards.interfaces.dtos.issuer.v1.IssuerDTO;
import com.catalis.core.banking.cards.models.entities.issuer.v1.Issuer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the Issuer entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IssuerMapper {

    /**
     * Convert a Issuer entity to a IssuerDTO.
     *
     * @param LIssuer the Issuer entity to convert
     * @return the resulting IssuerDTO
     */
    IssuerDTO toDTO(Issuer LIssuer);

    /**
     * Convert a IssuerDTO to a Issuer entity.
     *
     * @param LIssuerLDLTLO the IssuerDTO to convert
     * @return the resulting Issuer entity
     */
    Issuer toEntity(IssuerDTO LIssuerLDLTLO);
}
