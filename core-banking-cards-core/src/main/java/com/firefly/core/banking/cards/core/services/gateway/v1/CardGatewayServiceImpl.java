package com.firefly.core.banking.cards.core.services.gateway.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.gateway.v1.CardGatewayMapper;
import com.firefly.core.banking.cards.interfaces.dtos.gateway.v1.CardGatewayDTO;
import com.firefly.core.banking.cards.models.entities.gateway.v1.CardGateway;
import com.firefly.core.banking.cards.models.repositories.gateway.v1.CardGatewayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

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
    public Mono<CardGatewayDTO> getGateway(UUID gatewayId) {
        return repository.findByGatewayId(gatewayId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardGatewayDTO> updateGateway(UUID gatewayId, CardGatewayDTO gatewayDTO) {
        return repository.findByGatewayId(gatewayId)
                .flatMap(existingGateway -> {
                    CardGateway updatedGateway = mapper.toEntity(gatewayDTO);
                    updatedGateway.setGatewayId(existingGateway.getGatewayId());
                    return repository.save(updatedGateway);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteGateway(UUID gatewayId) {
        return repository.findByGatewayId(gatewayId)
                .flatMap(repository::delete);
    }
}
