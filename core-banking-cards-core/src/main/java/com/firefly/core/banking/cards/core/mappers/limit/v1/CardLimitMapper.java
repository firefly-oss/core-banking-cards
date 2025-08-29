package com.firefly.core.banking.cards.core.mappers.limit.v1;

import com.firefly.core.banking.cards.interfaces.dtos.limit.v1.CardLimitDTO;
import com.firefly.core.banking.cards.interfaces.enums.limit.v1.LimitTypeEnum;
import com.firefly.core.banking.cards.interfaces.enums.limit.v1.ResetPeriodEnum;
import com.firefly.core.banking.cards.models.entities.limit.v1.CardLimit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Named;

/**
 * Mapper for the CardLimit entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardLimitMapper {

    /**
     * Convert a CardLimit entity to a CardLimitDTO.
     *
     * @param LCardLLimit the CardLimit entity to convert
     * @return the resulting CardLimitDTO
     */
    CardLimitDTO toDTO(CardLimit LCardLLimit);

    /**
     * Convert a CardLimitDTO to a CardLimit entity.
     *
     * @param LCardLLimitLDLTLO the CardLimitDTO to convert
     * @return the resulting CardLimit entity
     */
    CardLimit toEntity(CardLimitDTO LCardLLimitLDLTLO);
}
