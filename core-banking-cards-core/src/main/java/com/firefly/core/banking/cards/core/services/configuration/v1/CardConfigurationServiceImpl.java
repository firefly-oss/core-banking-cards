package com.firefly.core.banking.cards.core.services.configuration.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.configuration.v1.CardConfigurationMapper;
import com.firefly.core.banking.cards.interfaces.dtos.configuration.v1.CardConfigurationDTO;
import com.firefly.core.banking.cards.models.entities.configuration.v1.CardConfiguration;
import com.firefly.core.banking.cards.models.repositories.configuration.v1.CardConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CardConfigurationServiceImpl implements CardConfigurationService {

    @Autowired
    private CardConfigurationRepository repository;

    @Autowired
    private CardConfigurationMapper mapper;

    @Override
    public Mono<PaginationResponse<CardConfigurationDTO>> listConfigurations(UUID cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardConfigurationDTO> createConfiguration(UUID cardId, CardConfigurationDTO configDTO) {
        configDTO.setCardId(cardId);
        CardConfiguration entity = mapper.toEntity(configDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardConfigurationDTO> getConfiguration(UUID cardId, UUID configId) {
        return repository.findByCardConfigurationId(configId)
                .filter(config -> config.getCardId().equals(cardId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardConfigurationDTO> updateConfiguration(UUID cardId, UUID configId, CardConfigurationDTO configDTO) {
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
    public Mono<Void> deleteConfiguration(UUID cardId, UUID configId) {
        return repository.findByCardConfigurationId(configId)
                .filter(config -> config.getCardId().equals(cardId))
                .flatMap(repository::delete);
    }
}