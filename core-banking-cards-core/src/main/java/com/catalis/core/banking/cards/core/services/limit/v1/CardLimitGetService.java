package com.catalis.core.banking.cards.core.services.limit.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.limit.v1.CardLimitMapper;
import com.catalis.core.banking.cards.interfaces.dtos.limit.v1.CardLimitDTO;
import com.catalis.core.banking.cards.models.repositories.limit.v1.CardLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class CardLimitGetService {

    @Autowired
    private CardLimitRepository cardLimitRepository;

    @Autowired
    private CardLimitMapper cardLimitMapper;

    /**
     * Retrieves the card limit details by its unique identifier.
     * The method fetches the card limit from the repository and maps it to a CardLimitDTO.
     *
     * @param cardLimitId the unique identifier of the card limit to fetch
     * @return a Mono emitting the CardLimitDTO corresponding to the provided ID
     */
    public Mono<CardLimitDTO> getCardLimitById(Long cardLimitId) {
        return cardLimitRepository.findById(cardLimitId)
                .map(cardLimitMapper::toDTO);
    }

    /**
     * Retrieves paginated card limit details for a specific card ID.
     * This method fetches a page of card limit data mapped to DTOs using
     * the provided pagination request and card ID.
     *
     * @param cardId the ID of the card for which the card limits are to be retrieved
     * @param paginationRequest the pagination parameters including page number, size, and sorting details
     * @return a Mono emitting a paginated response containing card limit DTOs
     */
    public Mono<PaginationResponse<CardLimitDTO>> getCardLimitByCardId(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                cardLimitMapper::toDTO,
                pageable -> cardLimitRepository.findByCardId(cardId, pageable),
                () -> cardLimitRepository.countByCardId(cardId)
        );
    }

    /**
     * Retrieves all card limits with pagination.
     * This method performs a paginated query to fetch card limit data from the repository.
     * The data is mapped to CardLimitDTO objects and returned in a paginated response.
     *
     * @param paginationRequest the pagination request containing page size and offset information
     * @return a Mono emitting a PaginationResponse containing the requested page of CardLimitDTO objects
     */
    public Mono<PaginationResponse<CardLimitDTO>> getAllCardLimits(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                cardLimitMapper::toDTO,
                pageable -> cardLimitRepository.findAllBy(pageable),
                () -> cardLimitRepository.count()
        );
    }

}
