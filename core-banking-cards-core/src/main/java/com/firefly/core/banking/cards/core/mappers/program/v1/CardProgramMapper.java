package com.firefly.core.banking.cards.core.mappers.program.v1;

import com.firefly.core.banking.cards.interfaces.dtos.program.v1.CardProgramDTO;
import com.firefly.core.banking.cards.models.entities.program.v1.CardProgram;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardProgram entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardProgramMapper {

    /**
     * Convert a CardProgram entity to a CardProgramDTO.
     *
     * @param LCardLProgram the CardProgram entity to convert
     * @return the resulting CardProgramDTO
     */
    CardProgramDTO toDTO(CardProgram LCardLProgram);

    /**
     * Convert a CardProgramDTO to a CardProgram entity.
     *
     * @param LCardLProgramLDLTLO the CardProgramDTO to convert
     * @return the resulting CardProgram entity
     */
    CardProgram toEntity(CardProgramDTO LCardLProgramLDLTLO);
}
