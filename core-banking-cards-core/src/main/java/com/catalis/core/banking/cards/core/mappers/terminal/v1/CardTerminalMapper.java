package com.catalis.core.banking.cards.core.mappers.terminal.v1;

import com.catalis.core.banking.cards.interfaces.dtos.terminal.v1.CardTerminalDTO;
import com.catalis.core.banking.cards.models.entities.terminal.v1.CardTerminal;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardTerminal entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardTerminalMapper {

    /**
     * Convert a CardTerminal entity to a CardTerminalDTO.
     *
     * @param cardTerminal the CardTerminal entity to convert
     * @return the resulting CardTerminalDTO
     */
    CardTerminalDTO toDTO(CardTerminal cardTerminal);

    /**
     * Convert a CardTerminalDTO to a CardTerminal entity.
     *
     * @param cardTerminalDTO the CardTerminalDTO to convert
     * @return the resulting CardTerminal entity
     */
    CardTerminal toEntity(CardTerminalDTO cardTerminalDTO);
}
