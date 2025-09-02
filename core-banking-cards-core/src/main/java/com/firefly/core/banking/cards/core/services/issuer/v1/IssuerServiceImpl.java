package com.firefly.core.banking.cards.core.services.issuer.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.issuer.v1.IssuerMapper;
import com.firefly.core.banking.cards.interfaces.dtos.issuer.v1.IssuerDTO;
import com.firefly.core.banking.cards.models.entities.issuer.v1.Issuer;
import com.firefly.core.banking.cards.models.repositories.issuer.v1.IssuerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class IssuerServiceImpl implements IssuerService {

    @Autowired
    private IssuerRepository repository;

    @Autowired
    private IssuerMapper mapper;

    @Override
    public Mono<PaginationResponse<IssuerDTO>> listIssuers(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                repository::findAllBy,
                repository::count
        );
    }

    @Override
    public Mono<IssuerDTO> createIssuer(IssuerDTO issuerDTO) {
        Issuer entity = mapper.toEntity(issuerDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<IssuerDTO> getIssuer(UUID issuerId) {
        return repository.findByIssuerId(issuerId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<IssuerDTO> updateIssuer(UUID issuerId, IssuerDTO issuerDTO) {
        return repository.findByIssuerId(issuerId)
                .flatMap(existingIssuer -> {
                    Issuer updatedIssuer = mapper.toEntity(issuerDTO);
                    updatedIssuer.setIssuerId(existingIssuer.getIssuerId());
                    return repository.save(updatedIssuer);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteIssuer(UUID issuerId) {
        return repository.findByIssuerId(issuerId)
                .flatMap(repository::delete);
    }
}
