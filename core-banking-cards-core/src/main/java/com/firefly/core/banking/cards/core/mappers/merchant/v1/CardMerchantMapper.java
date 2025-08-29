package com.firefly.core.banking.cards.core.mappers.merchant.v1;

import com.firefly.core.banking.cards.interfaces.dtos.merchant.v1.CardMerchantDTO;
import com.firefly.core.banking.cards.models.entities.merchant.v1.CardMerchant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardMerchant entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMerchantMapper {

    /**
     * Convert a CardMerchant entity to a CardMerchantDTO.
     *
     * @param cardMerchant the CardMerchant entity to convert
     * @return the resulting CardMerchantDTO
     */
    CardMerchantDTO toDTO(CardMerchant cardMerchant);

    /**
     * Convert a CardMerchantDTO to a CardMerchant entity.
     *
     * @param cardMerchantDTO the CardMerchantDTO to convert
     * @return the resulting CardMerchant entity
     */
    CardMerchant toEntity(CardMerchantDTO cardMerchantDTO);
}
