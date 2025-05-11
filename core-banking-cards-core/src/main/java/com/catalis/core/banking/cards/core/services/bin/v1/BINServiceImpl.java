package com.catalis.core.banking.cards.core.services.bin.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.bin.v1.BINMapper;
import com.catalis.core.banking.cards.interfaces.dtos.bin.v1.BINDTO;
import com.catalis.core.banking.cards.models.entities.bin.v1.BIN;
import com.catalis.core.banking.cards.models.repositories.bin.v1.BINRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class BINServiceImpl implements BINService {

    @Autowired
    private BINRepository repository;

    @Autowired
    private BINMapper mapper;

    @Override
    public Mono<PaginationResponse<BINDTO>> listBINs(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                repository::findAllBy,
                repository::count
        );
    }

    @Override
    public Mono<BINDTO> createBIN(BINDTO binDTO) {
        BIN entity = mapper.toEntity(binDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BINDTO> getBIN(Long binId) {
        return repository.findByBinId(binId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BINDTO> getBINByNumber(String binNumber) {
        return repository.findByBinNumber(binNumber)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BINDTO> updateBIN(Long binId, BINDTO binDTO) {
        return repository.findByBinId(binId)
                .flatMap(existingBIN -> {
                    BIN updatedBIN = mapper.toEntity(binDTO);
                    updatedBIN.setBinId(existingBIN.getBinId());
                    return repository.save(updatedBIN);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteBIN(Long binId) {
        return repository.findByBinId(binId)
                .flatMap(repository::delete);
    }
}
