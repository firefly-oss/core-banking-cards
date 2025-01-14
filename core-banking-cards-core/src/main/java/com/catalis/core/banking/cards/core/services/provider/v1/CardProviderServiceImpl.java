package com.catalis.core.banking.cards.core.services.provider.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.provider.v1.CardProviderMapper;
import com.catalis.core.banking.cards.interfaces.dtos.provider.v1.CardProviderDTO;
import com.catalis.core.banking.cards.models.entities.provider.v1.CardProvider;
import com.catalis.core.banking.cards.models.repositories.provider.v1.CardProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardProviderServiceImpl implements CardProviderService {

    @Autowired
    private CardProviderRepository repository;

    @Autowired
    private CardProviderMapper mapper;

    @Override
    public Mono<PaginationResponse<CardProviderDTO>> listProviders(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardProviderDTO> createProvider(Long cardId, CardProviderDTO providerDTO) {
        CardProvider cardProvider = mapper.toEntity(providerDTO);
        cardProvider.setCardId(cardId);
        return repository.save(cardProvider)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardProviderDTO> getProvider(Long cardId, Long providerId) {
        return repository.findById(providerId)
                .filter(provider -> provider.getCardId().equals(cardId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardProviderDTO> updateProvider(Long cardId, Long providerId, CardProviderDTO providerDTO) {
        return repository.findById(providerId)
                .filter(provider -> provider.getCardId().equals(cardId))
                .flatMap(existing -> {
                    CardProvider toUpdate = mapper.toEntity(providerDTO);
                    toUpdate.setCardProviderId(providerId);
                    toUpdate.setCardId(cardId);
                    return repository.save(toUpdate);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteProvider(Long cardId, Long providerId) {
        return repository.findById(providerId)
                .filter(provider -> provider.getCardId().equals(cardId))
                .flatMap(repository::delete);
    }
}
