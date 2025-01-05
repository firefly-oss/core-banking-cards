package com.catalis.core.banking.cards.models.repositories.transaction.v1;

import com.catalis.core.banking.cards.interfaces.dtos.transaction.v1.CardTransactionFilterDTO;
import com.catalis.core.banking.cards.models.entities.transaction.v1.CardTransaction;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CardTransactionRepository extends BaseRepository<CardTransaction, Long> {

    Mono<CardTransaction> findByCardTransactionId(Long cardTransactionId);

    Flux<CardTransaction> findByCardId(Long cardId, Pageable pageable);
    Mono<Long> countByCardId(Long cardId);

    @Query("""
        SELECT *
        FROM card_transaction
        WHERE
            (:#{#filterDTO.cardId} IS NULL
              OR card_id = :#{#filterDTO.cardId})
            AND (:#{#filterDTO.transactionType} IS NULL
              OR transaction_type = :#{#filterDTO.transactionType.name()})
            AND (:#{#filterDTO.transactionStatus} IS NULL
              OR transaction_status = :#{#filterDTO.transactionStatus.name()})
            AND (:#{#filterDTO.cardMerchantName} IS NULL
              OR card_merchant_name = :#{#filterDTO.cardMerchantName})
            AND (:#{#filterDTO.fromTimestamp} IS NULL
              OR card_transaction_timestamp >= :#{#filterDTO.fromTimestamp})
            AND (:#{#filterDTO.toTimestamp} IS NULL
              OR card_transaction_timestamp <= :#{#filterDTO.toTimestamp})
            AND (:#{#filterDTO.cardFraudFlag} IS NULL
              OR card_fraud_flag = :#{#filterDTO.cardFraudFlag})
            AND (:#{#filterDTO.cardHolderCountry} IS NULL
              OR card_holder_country = :#{#filterDTO.cardHolderCountry})
            AND (:#{#filterDTO.minAmount} IS NULL
              OR transaction_amount >= :#{#filterDTO.minAmount})
            AND (:#{#filterDTO.maxAmount} IS NULL
              OR transaction_amount <= :#{#filterDTO.maxAmount})
        ORDER BY
            CASE
                WHEN :#{#pageable.sort.isEmpty()} THEN card_transaction_id
                ELSE :#{#pageable.sort.iterator().next().property}
            END
            /* direction: ASC or DESC */
            :#{#pageable.sort.isEmpty() 
                ? 'ASC' 
                : (#pageable.sort.iterator().next().ascending ? 'ASC' : 'DESC')}
        OFFSET :#{#pageable.offset}
        LIMIT :#{#pageable.pageSize}
        """)
    Flux<CardTransaction> filterTransactionsByCriteria(CardTransactionFilterDTO filterDTO, Pageable pageable);



    @Query("""
        SELECT COUNT(*)
        FROM card_transaction
        WHERE
            (:#{#filterDTO.cardId} IS NULL
              OR card_id = :#{#filterDTO.cardId})
            AND (:#{#filterDTO.transactionType} IS NULL
              OR transaction_type = :#{#filterDTO.transactionType.name()})
            AND (:#{#filterDTO.transactionStatus} IS NULL
              OR transaction_status = :#{#filterDTO.transactionStatus.name()})
            AND (:#{#filterDTO.cardMerchantName} IS NULL
              OR card_merchant_name = :#{#filterDTO.cardMerchantName})
            AND (:#{#filterDTO.fromTimestamp} IS NULL
              OR card_transaction_timestamp >= :#{#filterDTO.fromTimestamp})
            AND (:#{#filterDTO.toTimestamp} IS NULL
              OR card_transaction_timestamp <= :#{#filterDTO.toTimestamp})
            AND (:#{#filterDTO.cardFraudFlag} IS NULL
              OR card_fraud_flag = :#{#filterDTO.cardFraudFlag})
            AND (:#{#filterDTO.cardHolderCountry} IS NULL
              OR card_holder_country = :#{#filterDTO.cardHolderCountry})
            AND (:#{#filterDTO.minAmount} IS NULL
              OR transaction_amount >= :#{#filterDTO.minAmount})
            AND (:#{#filterDTO.maxAmount} IS NULL
              OR transaction_amount <= :#{#filterDTO.maxAmount})
        """)
    Mono<Long> countTransactionsByCriteria(CardTransactionFilterDTO filterDTO);

}