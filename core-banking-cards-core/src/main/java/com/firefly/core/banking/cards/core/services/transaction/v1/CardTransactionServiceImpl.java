package com.firefly.core.banking.cards.core.services.transaction.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.transaction.v1.CardTransactionMapper;
import com.firefly.core.banking.cards.interfaces.dtos.transaction.v1.CardTransactionDTO;
import com.firefly.core.banking.cards.models.entities.transaction.v1.CardTransaction;
import com.firefly.core.banking.cards.models.repositories.transaction.v1.CardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CardTransactionServiceImpl implements CardTransactionService {

    @Autowired
    private CardTransactionRepository repository;

    @Autowired
    private CardTransactionMapper mapper;

    @Override
    public Mono<PaginationResponse<CardTransactionDTO>> listTransactions(UUID cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardTransactionDTO> createTransaction(UUID cardId, CardTransactionDTO transactionDTO) {
        transactionDTO.setCardId(cardId);
        return repository.save(mapper.toEntity(transactionDTO))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardTransactionDTO> getTransaction(UUID cardId, UUID transactionId) {
        return repository.findByCardTransactionId(transactionId)
                .filter(transaction -> cardId.equals(transaction.getCardId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<PaginationResponse<CardTransactionDTO>> findFiltered(FilterRequest<CardTransactionDTO> request) {
        return FilterUtils
                .createFilter(
                        CardTransaction.class,
                        mapper::toDTO
                )
                .filter(request);
    }

    @Override
    public Mono<CardTransactionDTO> updateTransaction(UUID cardId, UUID transactionId, CardTransactionDTO transactionDTO) {
        return repository.findByCardTransactionId(transactionId)
                .filter(transaction -> cardId.equals(transaction.getCardId()))
                .flatMap(existingTransaction -> {
                    CardTransaction updatedEntity = mapper.toEntity(transactionDTO);
                    updatedEntity.setCardTransactionId(existingTransaction.getCardTransactionId());
                    updatedEntity.setCardId(existingTransaction.getCardId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteTransaction(UUID cardId, UUID transactionId) {
        return repository.findByCardTransactionId(transactionId)
                .filter(transaction -> cardId.equals(transaction.getCardId()))
                .flatMap(repository::delete);
    }
}
