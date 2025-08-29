package com.firefly.core.banking.cards.core.mappers.reward.v1;

import com.firefly.core.banking.cards.interfaces.dtos.reward.v1.CardRewardDTO;
import com.firefly.core.banking.cards.models.entities.reward.v1.CardReward;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardReward entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardRewardMapper {

    /**
     * Convert a CardReward entity to a CardRewardDTO.
     *
     * @param LCardLReward the CardReward entity to convert
     * @return the resulting CardRewardDTO
     */
    CardRewardDTO toDTO(CardReward LCardLReward);

    /**
     * Convert a CardRewardDTO to a CardReward entity.
     *
     * @param LCardLRewardLDLTLO the CardRewardDTO to convert
     * @return the resulting CardReward entity
     */
    CardReward toEntity(CardRewardDTO LCardLRewardLDLTLO);
}
