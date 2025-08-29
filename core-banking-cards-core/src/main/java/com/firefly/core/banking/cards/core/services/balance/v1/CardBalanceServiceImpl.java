package com.firefly.core.banking.cards.core.services.balance.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.balance.v1.CardBalanceMapper;
import com.firefly.core.banking.cards.interfaces.dtos.balance.v1.CardBalanceDTO;
import com.firefly.core.banking.cards.models.entities.balance.v1.CardBalance;
import com.firefly.core.banking.cards.models.repositories.balance.v1.CardBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardBalanceServiceImpl implements CardBalanceService {

    @Autowired
    private CardBalanceRepository repository;

    @Autowired
    private CardBalanceMapper mapper;

    @Override
    public Mono<PaginationResponse<CardBalanceDTO>> listBalances(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardBalanceDTO> createBalance(Long cardId, CardBalanceDTO balanceDTO) {
        balanceDTO.setCardId(cardId);
        CardBalance entity = mapper.toEntity(balanceDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardBalanceDTO> getBalance(Long cardId, Long balanceId) {
        return repository.findByBalanceId(balanceId)
                .flatMap(entity -> {
                    if (!entity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardBalance not found for the specified card."));
                    }
                    return Mono.just(mapper.toDTO(entity));
                });
    }

    @Override
    public Mono<CardBalanceDTO> updateBalance(Long cardId, Long balanceId, CardBalanceDTO balanceDTO) {
        return repository.findByBalanceId(balanceId)
                .flatMap(existingBalance -> {
                    if (!existingBalance.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardBalance not found for the specified card."));
                    }
                    
                    CardBalance updatedBalance = mapper.toEntity(balanceDTO);
                    updatedBalance.setBalanceId(existingBalance.getBalanceId());
                    updatedBalance.setCardId(cardId);
                    
                    return repository.save(updatedBalance);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteBalance(Long cardId, Long balanceId) {
        return repository.findByBalanceId(balanceId)
                .flatMap(balance -> {
                    if (!balance.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardBalance not found for the specified card."));
                    }
                    return repository.delete(balance);
                });
    }
}
