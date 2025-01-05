package com.catalis.core.banking.cards.core.services.virtual.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.virtual.v1.VirtualCardMapper;
import com.catalis.core.banking.cards.interfaces.dtos.virtual.v1.VirtualCardDTO;
import com.catalis.core.banking.cards.models.repositories.virtual.v1.VirtualCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class VirtualCardGetService {

    @Autowired
    private VirtualCardRepository virtualCardRepository;

    @Autowired
    private VirtualCardMapper virtualCardMapper;

    /**
     * Retrieves a virtual card by its unique identifier.
     *
     * @param virtualCardId the unique identifier of the virtual card to be retrieved
     * @return a Mono containing the VirtualCardDTO if found, or an empty Mono if the virtual card does not exist
     */
    public Mono<VirtualCardDTO> getVirtualCardById(Long virtualCardId) {
        return virtualCardRepository.findById(virtualCardId)
                .map(virtualCardMapper::toDTO);
    }

    /**
     * Retrieves a paginated list of virtual cards associated with a specific card ID.
     *
     * @param cardId the unique identifier of the card for which virtual cards will be retrieved
     * @param paginationRequest the request object containing pagination details such as page number and page size
     * @return a Mono emitting a PaginationResponse containing a list of VirtualCardDTO objects and metadata for pagination
     */
    public Mono<PaginationResponse<VirtualCardDTO>> getVirtualCardsByCardId(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                virtualCardMapper::toDTO,
                pageable -> virtualCardRepository.findByCardId(cardId, pageable),
                () -> virtualCardRepository.countByCardId(cardId)
        );
    }

    /**
     * Retrieves a paginated list of virtual cards.
     *
     * @param paginationRequest the request object containing pagination details such as page number and size
     * @return a Mono emitting a PaginationResponse containing a list of VirtualCardDTO objects and pagination metadata
     */
    public Mono<PaginationResponse<VirtualCardDTO>> getAllVirtualCards(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                virtualCardMapper::toDTO,
                pageable -> virtualCardRepository.findAllBy(pageable),
                () -> virtualCardRepository.count()
        );
    }
}
