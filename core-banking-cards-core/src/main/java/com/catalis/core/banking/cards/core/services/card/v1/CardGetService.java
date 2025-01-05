package com.catalis.core.banking.cards.core.services.card.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.card.v1.CardMapper;
import com.catalis.core.banking.cards.interfaces.dtos.card.v1.CardDTO;
import com.catalis.core.banking.cards.models.repositories.card.v1.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class CardGetService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;

    /**
     * Retrieves a card by its unique identifier.
     * The method fetches the card entity from the repository, maps it to a DTO,
     **/
    public Mono<CardDTO> getCardById(Long cardId) {
        return cardRepository.findById(cardId)
                .map(cardMapper::toDTO);
    }

    /**
     * Retrieves a paginated list of all cards based on the provided pagination request.
     * The method utilizes pagination utilities to fetch the data in a paginated manner,
     * map each card entity to its corresponding DTO,*/
    public Mono<PaginationResponse<CardDTO>> getAllCards(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                cardMapper::toDTO,
                pageable -> cardRepository.findAllBy(pageable),
                () -> cardRepository.count()
        );
    }

    /**
     * Retrieves a paginated list of cards associated with a specific account ID.
     * This method uses the account ID to filter the cards and returns the results
     * in a paginated format based on the provided pagination request.
     *
     * @param accountId The ID of the account associated with the cards.
     * @param paginationRequest The pagination details including page number, size, and sort options.
     * @return A Mono emitting the paginated response containing CardDTO objects.
     */
    public Mono<PaginationResponse<CardDTO>> getAllCardsByAccountId(Long accountId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                cardMapper::toDTO,
                pageable -> cardRepository.findByAccountId(accountId, pageable),
                () -> cardRepository.countByAccountId(accountId)
        );
    }
}