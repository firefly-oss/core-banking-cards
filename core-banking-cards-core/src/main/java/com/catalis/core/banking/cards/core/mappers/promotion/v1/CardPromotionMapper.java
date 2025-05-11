package com.catalis.core.banking.cards.core.mappers.promotion.v1;

import com.catalis.core.banking.cards.interfaces.dtos.promotion.v1.CardPromotionDTO;
import com.catalis.core.banking.cards.models.entities.promotion.v1.CardPromotion;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardPromotion entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardPromotionMapper {

    /**
     * Convert a CardPromotion entity to a CardPromotionDTO.
     *
     * @param cardPromotion the CardPromotion entity to convert
     * @return the resulting CardPromotionDTO
     */
    CardPromotionDTO toDTO(CardPromotion cardPromotion);

    /**
     * Convert a CardPromotionDTO to a CardPromotion entity.
     *
     * @param cardPromotionDTO the CardPromotionDTO to convert
     * @return the resulting CardPromotion entity
     */
    CardPromotion toEntity(CardPromotionDTO cardPromotionDTO);
}
