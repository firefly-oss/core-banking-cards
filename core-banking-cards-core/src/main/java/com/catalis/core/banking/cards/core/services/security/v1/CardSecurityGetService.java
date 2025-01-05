package com.catalis.core.banking.cards.core.services.security.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.security.v1.CardSecurityMapper;
import com.catalis.core.banking.cards.interfaces.dtos.security.v1.CardSecurityDTO;
import com.catalis.core.banking.cards.models.repositories.security.v1.CardSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class CardSecurityGetService {

    @Autowired
    private CardSecurityRepository cardSecurityRepository;

    @Autowired
    private CardSecurityMapper cardSecurityMapper;

    /**
     * Retrieves a CardSecurityDTO object by its unique ID.
     * Maps the retrieved entity to a DTO using the CardSecurityMapper.
     *
     * @param cardSecurityId the unique ID of the card security record to retrieve
     * @return a Mono emitting the retrieved CardSecurityDTO object, or an empty Mono if no record is found
     */
    public Mono<CardSecurityDTO> getCardSecurityById(Long cardSecurityId) {
        return cardSecurityRepository.findById(cardSecurityId)
                .map(cardSecurityMapper::toDTO);
    }

    /**
     * Retrieves a paginated list of card security details associated with the specified card ID.
     * Maps the resulting entities to DTOs and also returns the total count of records for the given card ID.
     *
     * @param cardId the ID of the card whose security details are to be fetched
     * @param paginationRequest the pagination request containing page number, size, and sorting options
     * @return a Mono emitting a PaginationResponse containing the list of CardSecurityDTO objects and pagination metadata
     */
    public Mono<PaginationResponse<CardSecurityDTO>> getCardSecurityByCardId(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                cardSecurityMapper::toDTO,
                pageable -> cardSecurityRepository.findByCardId(cardId, pageable),
                () -> cardSecurityRepository.countByCardId(cardId)
        );
    }

    /**
     * Retrieves a paginated list of all card security records.
     * Maps the database entities to CardSecurityDTO objects using the cardSecurityMapper.
     *
     * @param paginationRequest the pagination request containing page number, size, and sort information
     * @return a Mono emitting a PaginationResponse containing a list of CardSecurityDTOs and pagination metadata
     */
    public Mono<PaginationResponse<CardSecurityDTO>> getAllCardSecurity(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                cardSecurityMapper::toDTO,
                pageable -> cardSecurityRepository.findAllBy(pageable),
                () -> cardSecurityRepository.count()
        );
    }

}