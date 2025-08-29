package com.firefly.core.banking.cards.core.services.acquirer.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.acquirer.v1.CardAcquirerMapper;
import com.firefly.core.banking.cards.interfaces.dtos.acquirer.v1.CardAcquirerDTO;
import com.firefly.core.banking.cards.models.entities.acquirer.v1.CardAcquirer;
import com.firefly.core.banking.cards.models.repositories.acquirer.v1.CardAcquirerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardAcquirerServiceImpl implements CardAcquirerService {

    @Autowired
    private CardAcquirerRepository repository;

    @Autowired
    private CardAcquirerMapper mapper;

    @Override
    public Mono<PaginationResponse<CardAcquirerDTO>> listAcquirers(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                repository::findAllBy,
                repository::count
        );
    }

    @Override
    public Mono<CardAcquirerDTO> createAcquirer(CardAcquirerDTO acquirerDTO) {
        CardAcquirer entity = mapper.toEntity(acquirerDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardAcquirerDTO> getAcquirer(Long acquirerId) {
        return repository.findByAcquirerId(acquirerId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardAcquirerDTO> updateAcquirer(Long acquirerId, CardAcquirerDTO acquirerDTO) {
        return repository.findByAcquirerId(acquirerId)
                .flatMap(existingAcquirer -> {
                    CardAcquirer updatedAcquirer = mapper.toEntity(acquirerDTO);
                    updatedAcquirer.setAcquirerId(existingAcquirer.getAcquirerId());
                    return repository.save(updatedAcquirer);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteAcquirer(Long acquirerId) {
        return repository.findByAcquirerId(acquirerId)
                .flatMap(repository::delete);
    }
}
