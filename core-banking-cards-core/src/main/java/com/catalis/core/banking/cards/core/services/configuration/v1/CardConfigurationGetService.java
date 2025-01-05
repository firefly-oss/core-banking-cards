package com.catalis.core.banking.cards.core.services.configuration.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.configuration.v1.CardConfigurationMapper;
import com.catalis.core.banking.cards.interfaces.dtos.configuration.v1.CardConfigurationDTO;
import com.catalis.core.banking.cards.models.repositories.configuration.v1.CardConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class CardConfigurationGetService {

    @Autowired
    private CardConfigurationRepository cardConfigurationRepository;

    @Autowired
    private CardConfigurationMapper cardConfigurationMapper;

    /**
     * Retrieves a card configuration by its unique identifier.
     * This method queries the repository for the specified card configuration
     * and maps the result into a CardConfigurationDTO object.
     *
     * @param cardConfigurationId the unique identifier of the card configuration to retrieve
     * @return a Mono emitting the CardConfigurationDTO corresponding to the provided ID, or an empty Mono if not found
     */
    public Mono<CardConfigurationDTO> getCardConfigurationById(Long cardConfigurationId) {
        return cardConfigurationRepository.findById(cardConfigurationId)
                .map(cardConfigurationMapper::toDTO);
    }

    /**
     * Retrieves a paginated list of card configurations associated with a specified card ID.
     * Converts the resulting entity objects into DTOs for the response.
     *
     * @param cardId the unique identifier of the card for which configurations are being fetched
     * @param paginationRequest the object containing pagination details such as page number and page size
     * @return a Mono emitting a PaginationResponse containing a list of CardConfigurationDTO objects and pagination metadata
     */
    public Mono<PaginationResponse<CardConfigurationDTO>> getCardConfigurationsByCardId(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                cardConfigurationMapper::toDTO,
                pageable -> cardConfigurationRepository.findByCardId(cardId, pageable),
                () -> cardConfigurationRepository.countByCardId(cardId)
        );
    }

    /**
     * Retrieves all card configurations with pagination.
     * The method fetches paginated card configurations from the repository,
     * maps them to CardConfigurationDTO objects, and returns the result
     * wrapped in a PaginationResponse.
     *
     * @param paginationRequest the PaginationRequest object containing pagination details such as page number and size
     * @return a Mono emitting a PaginationResponse containing the list of CardConfigurationDTO objects and pagination metadata
     */
    public Mono<PaginationResponse<CardConfigurationDTO>> getAllCardConfigurations(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                cardConfigurationMapper::toDTO,
                pageable -> cardConfigurationRepository.findAllBy(pageable),
                () -> cardConfigurationRepository.count()
        );
    }

}