package com.catalis.core.banking.cards.core.mappers.customer.v1;

import com.catalis.core.banking.cards.interfaces.dtos.customer.v1.CardCustomerDTO;
import com.catalis.core.banking.cards.models.entities.customer.v1.CardCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardCustomer entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardCustomerMapper {

    /**
     * Convert a CardCustomer entity to a CardCustomerDTO.
     *
     * @param cardCustomer the CardCustomer entity to convert
     * @return the resulting CardCustomerDTO
     */
    CardCustomerDTO toDTO(CardCustomer cardCustomer);

    /**
     * Convert a CardCustomerDTO to a CardCustomer entity.
     *
     * @param cardCustomerDTO the CardCustomerDTO to convert
     * @return the resulting CardCustomer entity
     */
    CardCustomer toEntity(CardCustomerDTO cardCustomerDTO);
}
