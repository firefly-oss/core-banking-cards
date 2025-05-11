package com.catalis.core.banking.cards.core.services.interest.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.interest.v1.CardInterestMapper;
import com.catalis.core.banking.cards.interfaces.dtos.interest.v1.CardInterestDTO;
import com.catalis.core.banking.cards.models.entities.interest.v1.CardInterest;
import com.catalis.core.banking.cards.models.repositories.interest.v1.CardInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardInterestServiceImpl implements CardInterestService {

    @Autowired
    private CardInterestRepository repository;

    @Autowired
    private CardInterestMapper mapper;

    @Override
    public Mono<PaginationResponse<CardInterestDTO>> listInterests(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardInterestDTO> createInterest(Long cardId, CardInterestDTO interestDTO) {
        interestDTO.setCardId(cardId);
        CardInterest entity = mapper.toEntity(interestDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardInterestDTO> getInterest(Long cardId, Long interestId) {
        return repository.findByInterestId(interestId)
                .flatMap(entity -> {
                    if (!entity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardInterest not found for the specified card."));
                    }
                    return Mono.just(mapper.toDTO(entity));
                });
    }

    @Override
    public Mono<CardInterestDTO> updateInterest(Long cardId, Long interestId, CardInterestDTO interestDTO) {
        return repository.findByInterestId(interestId)
                .flatMap(existingInterest -> {
                    if (!existingInterest.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardInterest not found for the specified card."));
                    }
                    
                    CardInterest updatedInterest = mapper.toEntity(interestDTO);
                    updatedInterest.setInterestId(existingInterest.getInterestId());
                    updatedInterest.setCardId(cardId);
                    
                    return repository.save(updatedInterest);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteInterest(Long cardId, Long interestId) {
        return repository.findByInterestId(interestId)
                .flatMap(interest -> {
                    if (!interest.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardInterest not found for the specified card."));
                    }
                    return repository.delete(interest);
                });
    }
}
