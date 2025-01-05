package com.catalis.core.banking.cards.core.services.provider.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.provider.v1.CardProviderMapper;
import com.catalis.core.banking.cards.interfaces.dtos.provider.v1.CardProviderDTO;
import com.catalis.core.banking.cards.models.repositories.provider.v1.CardProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class CardProviderGetService {

    @Autowired
    private CardProviderRepository cardProviderRepository;

    @Autowired
    private CardProviderMapper cardProviderMapper;

    /**
     * Retrieves a CardProvider by its ID and maps it to a CardProviderDTO.
     *
     * @param cardProviderId the ID of the CardProvider to retrieve.
     * @return a Mono emitting the CardProviderDTO corresponding to the given ID, or an empty Mono if no match is found.
     */
    public Mono<CardProviderDTO> getCardProviderById(Long cardProviderId) {
        return cardProviderRepository.findById(cardProviderId)
                .map(cardProviderMapper::toDTO);
    }


    /**
     * Retrieves a paginated list of CardProviderDTOs associated with a given card ID.
     *
     * @param cardId the ID of the card whose providers are to be retrieved.
     * @param paginationRequest the pagination parameters including page number and size.
     * @return a Mono emitting a PaginationResponse containing a list of CardProviderDTO objects,
     *         representing the providers associated with the specified card ID.
     */
    public Mono<PaginationResponse<CardProviderDTO>> getCardProvidersByCardId(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                cardProviderMapper::toDTO,
                pageable -> cardProviderRepository.findByCardId(cardId, pageable),
                () -> cardProviderRepository.countByCardId(cardId)
        );
    }

    /**
     * Retrieves a paginated list of all card providers.
     *
     * @param paginationRequest the request object containing pagination parameters such as page number, page size, and sorting criteria.
     * @return a Mono emitting a PaginationResponse containing a list of CardProviderDTOs and pagination metadata.
     */
    public Mono<PaginationResponse<CardProviderDTO>> getAllCardProviders(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                cardProviderMapper::toDTO,
                pageable -> cardProviderRepository.findAllBy(pageable),
                () -> cardProviderRepository.count()
        );
    }

}