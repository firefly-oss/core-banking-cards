package com.catalis.core.banking.cards.core.services.physical.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.physical.v1.PhysicalCardMapper;
import com.catalis.core.banking.cards.interfaces.dtos.physical.v1.PhysicalCardDTO;
import com.catalis.core.banking.cards.models.repositories.physical.v1.PhysicalCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class PhysicalCardGetService {

    @Autowired
    private PhysicalCardRepository physicalCardRepository;

    @Autowired
    private PhysicalCardMapper physicalCardMapper;

    /**
     * Retrieves a physical card by its unique identifier.
     *
     * @param physicalCardId the unique ID of the physical card to retrieve
     * @return a Mono emitting the PhysicalCardDTO object representing the physical card if found,
     *         or an empty Mono if no card is found with the given ID
     */
    public Mono<PhysicalCardDTO> getPhysicalCardById(Long physicalCardId) {
        return physicalCardRepository.findById(physicalCardId)
                .map(physicalCardMapper::toDTO);
    }

    public Mono<PhysicalCardDTO> getPhysicalCardByCardNumber(String cardNumber) {
        return physicalCardRepository.findByCardNumber(cardNumber)
                .map(physicalCardMapper::toDTO);
    }

    /**
     * Retrieves a paginated list of physical cards associated with a specific card ID.
     *
     * @param cardId the unique ID of the card to retrieve associated physical cards
     * @param paginationRequest the pagination request containing page size, page number, and sorting details
     * @return a Mono emitting a PaginationResponse containing a list of PhysicalCardDTO objects and pagination metadata
     */
    public Mono<PaginationResponse<PhysicalCardDTO>> getPhysicalCardByCardId(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                physicalCardMapper::toDTO,
                pageable -> physicalCardRepository.findByCardId(cardId, pageable),
                () -> physicalCardRepository.countByCardId(cardId)
        );
    }

    /**
     * Retrieves a paginated list of all physical cards in the system based on the given pagination request.
     *
     * @param paginationRequest the {@link PaginationRequest} object containing pagination parameters such as page number, page size, or sorting options
     * @return a {@link Mono} emitting a {@link PaginationResponse} containing a list of {@link PhysicalCardDTO} objects with pagination metadata
     */
    public Mono<PaginationResponse<PhysicalCardDTO>> getAllPhysicalCards(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                physicalCardMapper::toDTO,
                pageable -> physicalCardRepository.findAllBy(pageable),
                () -> physicalCardRepository.count()
        );
    }

}
