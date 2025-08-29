package com.firefly.core.banking.cards.core.services.card.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.core.mappers.card.v1.CardMapper;
import com.firefly.core.banking.cards.interfaces.dtos.card.v1.CardDTO;
import com.firefly.core.banking.cards.models.entities.card.v1.Card;
import com.firefly.core.banking.cards.models.repositories.card.v1.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository repository;

    @Autowired
    private CardMapper mapper;

    @Override
    public Mono<PaginationResponse<CardDTO>> filterCards(FilterRequest<CardDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        Card.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<CardDTO> createCard(CardDTO cardDTO) {
        return repository.save(mapper.toEntity(cardDTO))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardDTO> getCard(Long cardId) {
        return repository.findByCardId(cardId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardDTO> updateCard(Long cardId, CardDTO cardDTO) {
        return repository.findByCardId(cardId)
                .flatMap(existingCard -> {
                    Card updatedCard = mapper.toEntity(cardDTO);
                    updatedCard.setCardId(existingCard.getCardId());
                    return repository.save(updatedCard);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteCard(Long cardId) {
        return repository.findByCardId(cardId)
                .flatMap(repository::delete);
    }
}