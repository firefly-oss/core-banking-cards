package com.catalis.core.banking.cards.core.services.gateway.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.gateway.v1.CardGatewayMapper;
import com.catalis.core.banking.cards.interfaces.dtos.gateway.v1.CardGatewayDTO;
import com.catalis.core.banking.cards.models.entities.gateway.v1.CardGateway;
import com.catalis.core.banking.cards.models.repositories.gateway.v1.CardGatewayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardGatewayServiceImpl implements CardGatewayService {

    @Autowired
    private CardGatewayRepository repository;

    @Autowired
    private CardGatewayMapper mapper;

    @Override
    public Mono<PaginationResponse<CardGatewayDTO>> listGateways(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                repository::findAllBy,
                repository::count
        );
    }

    @Override
    public Mono<CardGatewayDTO> createGateway(CardGatewayDTO gatewayDTO) {
        CardGateway entity = mapper.toEntity(gatewayDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardGatewayDTO> getGateway(Long gatewayId) {
        return repository.findByGatewayId(gatewayId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardGatewayDTO> updateGateway(Long gatewayId, CardGatewayDTO gatewayDTO) {
        return repository.findByGatewayId(gatewayId)
                .flatMap(existingGateway -> {
                    CardGateway updatedGateway = mapper.toEntity(gatewayDTO);
                    updatedGateway.setGatewayId(existingGateway.getGatewayId());
                    return repository.save(updatedGateway);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteGateway(Long gatewayId) {
        return repository.findByGatewayId(gatewayId)
                .flatMap(repository::delete);
    }
}
