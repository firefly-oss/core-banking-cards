package com.catalis.core.banking.cards.core.services.transaction.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.transaction.v1.CardTransactionMapper;
import com.catalis.core.banking.cards.interfaces.dtos.transaction.v1.CardTransactionDTO;
import com.catalis.core.banking.cards.interfaces.dtos.transaction.v1.CardTransactionFilterDTO;
import com.catalis.core.banking.cards.models.repositories.transaction.v1.CardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class CardTransactionGetService {

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @Autowired
    private CardTransactionMapper cardTransactionMapper;

    /**
     * Retrieves a card transaction by its unique identifier.
     * Fetches the transaction data from the underlying repository and maps it to a DTO.
     *
     * @param transactionId the ID of the card transaction to retrieve
     * @return a {@link Mono} emitting the {@link CardTransactionDTO} if found, or empty if no transaction exists with the specified ID
     */
    public Mono<CardTransactionDTO> getTransactionById(Long transactionId) {
        return cardTransactionRepository.findById(transactionId)
                .map(cardTransactionMapper::toDTO);
    }

    /**
     * Retrieves a paginated list of all card transactions.
     *
     * @param paginationRequest the {@link PaginationRequest} specifying pagination parameters such as page number and size
     * @return a {@link Mono} emitting a {@link PaginationResponse} containing a list of {@link CardTransactionDTO} objects
     */
    public Mono<PaginationResponse<CardTransactionDTO>> getAllTransactions(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                cardTransactionMapper::toDTO,
                pageable -> cardTransactionRepository.findAllBy(pageable),
                () -> cardTransactionRepository.count()
        );
    }

    /**
     * Retrieves a paginated list of card transactions associated with a specific card ID.
     *
     * @param cardId the ID of the card for which transactions should be retrieved
     * @param paginationRequest an object specifying pagination parameters such as page number, page size, and sorting
     * @return a {@link Mono} emitting a {@link PaginationResponse} containing a list of {@link CardTransactionDTO} objects
     *         and pagination metadata
     */
    public Mono<PaginationResponse<CardTransactionDTO>> getAllTransactionsByCardId(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                cardTransactionMapper::toDTO,
                pageable -> cardTransactionRepository.findByCardId(cardId, pageable),
                () -> cardTransactionRepository.countByCardId(cardId)
        );
    }

    /**
     * Filters card transactions based on the given filter criteria and pagination request.
     *
     * @param filterDTO the filter criteria for card transactions, including fields like cardId,
     *                  transactionType, cardMerchantName, date ranges, and more.
     * @param paginationRequest the pagination request specifying the page size, page number, and sorting.
     * @return a Mono that emits a PaginationResponse containing a list of filtered CardTransactionDTO objects
     *         and pagination metadata.
     */
    public Mono<PaginationResponse<CardTransactionDTO>> filterTransactions(
            CardTransactionFilterDTO filterDTO,
            PaginationRequest paginationRequest
    ) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                cardTransactionMapper::toDTO,
                pageable -> cardTransactionRepository.filterTransactionsByCriteria(filterDTO, pageable),
                () -> cardTransactionRepository.countTransactionsByCriteria(filterDTO)
        );
    }
}
