package com.catalis.core.banking.cards.core.services.configuration.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.configuration.v1.CardConfigurationMapper;
import com.catalis.core.banking.cards.interfaces.dtos.configuration.v1.CardConfigurationDTO;
import com.catalis.core.banking.cards.models.entities.configuration.v1.CardConfiguration;
import com.catalis.core.banking.cards.models.repositories.configuration.v1.CardConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardConfigurationServiceImpl implements CardConfigurationService {

    @Autowired
    private CardConfigurationRepository repository;

    @Autowired
    private CardConfigurationMapper mapper;

    @Override
    public Mono<PaginationResponse<CardConfigurationDTO>> listConfigurations(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardConfigurationDTO> createConfiguration(Long cardId, CardConfigurationDTO configDTO) {
        configDTO.setCardId(cardId);
        CardConfiguration entity = mapper.toEntity(configDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardConfigurationDTO> getConfiguration(Long cardId, Long configId) {
        return repository.findByCardConfigurationId(configId)
                .filter(config -> config.getCardId().equals(cardId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardConfigurationDTO> updateConfiguration(Long cardId, Long configId, CardConfigurationDTO configDTO) {
        return repository.findByCardConfigurationId(configId)
                .filter(config -> config.getCardId().equals(cardId))
                .flatMap(existingConfig -> {
                    existingConfig.setConfigType(configDTO.getConfigType());
                    existingConfig.setConfigValue(configDTO.getConfigValue());
                    return repository.save(existingConfig);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteConfiguration(Long cardId, Long configId) {
        return repository.findByCardConfigurationId(configId)
                .filter(config -> config.getCardId().equals(cardId))
                .flatMap(repository::delete);
    }
}