package com.firefly.core.banking.cards.core.mappers.enrollment.v1;

import com.firefly.core.banking.cards.interfaces.dtos.enrollment.v1.CardEnrollmentDTO;
import com.firefly.core.banking.cards.models.entities.enrollment.v1.CardEnrollment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardEnrollment entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardEnrollmentMapper {

    /**
     * Convert a CardEnrollment entity to a CardEnrollmentDTO.
     *
     * @param LCardLEnrollment the CardEnrollment entity to convert
     * @return the resulting CardEnrollmentDTO
     */
    CardEnrollmentDTO toDTO(CardEnrollment LCardLEnrollment);

    /**
     * Convert a CardEnrollmentDTO to a CardEnrollment entity.
     *
     * @param LCardLEnrollmentLDLTLO the CardEnrollmentDTO to convert
     * @return the resulting CardEnrollment entity
     */
    CardEnrollment toEntity(CardEnrollmentDTO LCardLEnrollmentLDLTLO);
}
