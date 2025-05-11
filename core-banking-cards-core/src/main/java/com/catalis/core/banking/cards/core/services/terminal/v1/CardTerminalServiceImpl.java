package com.catalis.core.banking.cards.core.services.terminal.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.terminal.v1.CardTerminalMapper;
import com.catalis.core.banking.cards.interfaces.dtos.terminal.v1.CardTerminalDTO;
import com.catalis.core.banking.cards.models.entities.terminal.v1.CardTerminal;
import com.catalis.core.banking.cards.models.repositories.terminal.v1.CardTerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardTerminalServiceImpl implements CardTerminalService {

    @Autowired
    private CardTerminalRepository repository;

    @Autowired
    private CardTerminalMapper mapper;

    @Override
    public Mono<PaginationResponse<CardTerminalDTO>> listTerminals(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                repository::findAllBy,
                repository::count
        );
    }

    @Override
    public Mono<CardTerminalDTO> createTerminal(CardTerminalDTO terminalDTO) {
        CardTerminal entity = mapper.toEntity(terminalDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardTerminalDTO> getTerminal(Long terminalId) {
        return repository.findByTerminalId(terminalId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardTerminalDTO> updateTerminal(Long terminalId, CardTerminalDTO terminalDTO) {
        return repository.findByTerminalId(terminalId)
                .flatMap(existingTerminal -> {
                    CardTerminal updatedTerminal = mapper.toEntity(terminalDTO);
                    updatedTerminal.setTerminalId(existingTerminal.getTerminalId());
                    return repository.save(updatedTerminal);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteTerminal(Long terminalId) {
        return repository.findByTerminalId(terminalId)
                .flatMap(repository::delete);
    }
}
