package com.firefly.core.banking.cards.core.services.program.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.program.v1.CardProgramMapper;
import com.firefly.core.banking.cards.interfaces.dtos.program.v1.CardProgramDTO;
import com.firefly.core.banking.cards.models.entities.program.v1.CardProgram;
import com.firefly.core.banking.cards.models.repositories.program.v1.CardProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardProgramServiceImpl implements CardProgramService {

    @Autowired
    private CardProgramRepository repository;

    @Autowired
    private CardProgramMapper mapper;

    @Override
    public Mono<PaginationResponse<CardProgramDTO>> listPrograms(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                repository::findAllBy,
                repository::count
        );
    }

    @Override
    public Mono<CardProgramDTO> createProgram(CardProgramDTO programDTO) {
        CardProgram entity = mapper.toEntity(programDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardProgramDTO> getProgram(Long programId) {
        return repository.findByProgramId(programId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardProgramDTO> updateProgram(Long programId, CardProgramDTO programDTO) {
        return repository.findByProgramId(programId)
                .flatMap(existingProgram -> {
                    CardProgram updatedProgram = mapper.toEntity(programDTO);
                    updatedProgram.setProgramId(existingProgram.getProgramId());
                    return repository.save(updatedProgram);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteProgram(Long programId) {
        return repository.findByProgramId(programId)
                .flatMap(repository::delete);
    }
}
