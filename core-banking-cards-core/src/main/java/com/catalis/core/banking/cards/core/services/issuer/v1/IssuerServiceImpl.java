package com.catalis.core.banking.cards.core.services.issuer.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.issuer.v1.IssuerMapper;
import com.catalis.core.banking.cards.interfaces.dtos.issuer.v1.IssuerDTO;
import com.catalis.core.banking.cards.models.entities.issuer.v1.Issuer;
import com.catalis.core.banking.cards.models.repositories.issuer.v1.IssuerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

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
    public Mono<IssuerDTO> getIssuer(Long issuerId) {
        return repository.findByIssuerId(issuerId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<IssuerDTO> updateIssuer(Long issuerId, IssuerDTO issuerDTO) {
        return repository.findByIssuerId(issuerId)
                .flatMap(existingIssuer -> {
                    Issuer updatedIssuer = mapper.toEntity(issuerDTO);
                    updatedIssuer.setIssuerId(existingIssuer.getIssuerId());
                    return repository.save(updatedIssuer);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteIssuer(Long issuerId) {
        return repository.findByIssuerId(issuerId)
                .flatMap(repository::delete);
    }
}
