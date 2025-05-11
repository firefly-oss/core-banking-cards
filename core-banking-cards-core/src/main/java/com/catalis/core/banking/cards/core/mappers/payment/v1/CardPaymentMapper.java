package com.catalis.core.banking.cards.core.mappers.payment.v1;

import com.catalis.core.banking.cards.interfaces.dtos.payment.v1.CardPaymentDTO;
import com.catalis.core.banking.cards.models.entities.payment.v1.CardPayment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardPayment entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardPaymentMapper {

    /**
     * Convert a CardPayment entity to a CardPaymentDTO.
     *
     * @param LCardLPayment the CardPayment entity to convert
     * @return the resulting CardPaymentDTO
     */
    CardPaymentDTO toDTO(CardPayment LCardLPayment);

    /**
     * Convert a CardPaymentDTO to a CardPayment entity.
     *
     * @param LCardLPaymentLDLTLO the CardPaymentDTO to convert
     * @return the resulting CardPayment entity
     */
    CardPayment toEntity(CardPaymentDTO LCardLPaymentLDLTLO);
}
