package com.catalis.core.banking.cards.core.services.network.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.network.v1.CardNetworkMapper;
import com.catalis.core.banking.cards.interfaces.dtos.network.v1.CardNetworkDTO;
import com.catalis.core.banking.cards.models.entities.network.v1.CardNetwork;
import com.catalis.core.banking.cards.models.repositories.network.v1.CardNetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardNetworkServiceImpl implements CardNetworkService {

    @Autowired
    private CardNetworkRepository repository;

    @Autowired
    private CardNetworkMapper mapper;

    @Override
    public Mono<PaginationResponse<CardNetworkDTO>> listNetworks(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                repository::findAllBy,
                repository::count
        );
    }

    @Override
    public Mono<CardNetworkDTO> createNetwork(CardNetworkDTO networkDTO) {
        CardNetwork entity = mapper.toEntity(networkDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardNetworkDTO> getNetwork(Long networkId) {
        return repository.findByCardNetworkId(networkId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardNetworkDTO> updateNetwork(Long networkId, CardNetworkDTO networkDTO) {
        return repository.findByCardNetworkId(networkId)
                .flatMap(existingNetwork -> {
                    CardNetwork updatedNetwork = mapper.toEntity(networkDTO);
                    updatedNetwork.setCardNetworkId(existingNetwork.getCardNetworkId());
                    return repository.save(updatedNetwork);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteNetwork(Long networkId) {
        return repository.findByCardNetworkId(networkId)
                .flatMap(repository::delete);
    }
}
