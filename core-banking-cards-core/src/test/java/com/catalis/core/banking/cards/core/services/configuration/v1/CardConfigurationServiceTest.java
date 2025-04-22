package com.catalis.core.banking.cards.core.services.configuration.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.configuration.v1.CardConfigurationMapper;
import com.catalis.core.banking.cards.interfaces.dtos.configuration.v1.CardConfigurationDTO;
import com.catalis.core.banking.cards.interfaces.enums.configuration.v1.ConfigTypeEnum;
import com.catalis.core.banking.cards.models.entities.configuration.v1.CardConfiguration;
import com.catalis.core.banking.cards.models.repositories.configuration.v1.CardConfigurationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardConfigurationServiceTest {

    @Mock
    private CardConfigurationRepository repository;

    @Mock
    private CardConfigurationMapper mapper;

    @InjectMocks
    private CardConfigurationServiceImpl service;

    private CardConfigurationDTO configDTO;
    private CardConfiguration configEntity;
    private final Long cardId = 1L;
    private final Long configId = 2L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        configDTO = CardConfigurationDTO.builder()
                .cardConfigurationId(configId)
                .cardId(cardId)
                .configType(ConfigTypeEnum.CONTACTLESS_ENABLED)
                .configValue(true)
                .build();

        configEntity = new CardConfiguration();
        configEntity.setCardConfigurationId(configId);
        configEntity.setCardId(cardId);
        configEntity.setConfigType(ConfigTypeEnum.CONTACTLESS_ENABLED);
        configEntity.setConfigValue(true);
    }

    @Test
    void listConfigurations_Success() {
        // Arrange
        PaginationRequest paginationRequest = new PaginationRequest();

        @SuppressWarnings("unchecked")
        PaginationResponse<CardConfigurationDTO> expectedResponse = mock(PaginationResponse.class);

        try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
            paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            )).thenReturn(Mono.just(expectedResponse));

            // Act & Assert
            StepVerifier.create(service.listConfigurations(cardId, paginationRequest))
                    .expectNext(expectedResponse)
                    .verifyComplete();

            paginationUtilsMocked.verify(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            ));
        }
    }

    @Test
    void createConfiguration_Success() {
        // Arrange
        when(mapper.toEntity(any(CardConfigurationDTO.class))).thenReturn(configEntity);
        when(repository.save(any(CardConfiguration.class))).thenReturn(Mono.just(configEntity));
        when(mapper.toDTO(any(CardConfiguration.class))).thenReturn(configDTO);

        // Act & Assert
        StepVerifier.create(service.createConfiguration(cardId, configDTO))
                .expectNext(configDTO)
                .verifyComplete();

        verify(mapper).toEntity(configDTO);
        verify(repository).save(configEntity);
        verify(mapper).toDTO(configEntity);
    }

    @Test
    void getConfiguration_Success() {
        // Arrange
        when(repository.findByCardConfigurationId(configId)).thenReturn(Mono.just(configEntity));
        when(mapper.toDTO(any(CardConfiguration.class))).thenReturn(configDTO);

        // Act & Assert
        StepVerifier.create(service.getConfiguration(cardId, configId))
                .expectNext(configDTO)
                .verifyComplete();

        verify(repository).findByCardConfigurationId(configId);
        verify(mapper).toDTO(configEntity);
    }

    @Test
    void getConfiguration_NotFound() {
        // Arrange
        when(repository.findByCardConfigurationId(configId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.getConfiguration(cardId, configId))
                .verifyComplete();

        verify(repository).findByCardConfigurationId(configId);
        verify(mapper, never()).toDTO(any(CardConfiguration.class));
    }

    @Test
    void getConfiguration_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByCardConfigurationId(configId)).thenReturn(Mono.just(configEntity));

        // Act & Assert
        StepVerifier.create(service.getConfiguration(wrongCardId, configId))
                .verifyComplete();

        verify(repository).findByCardConfigurationId(configId);
        verify(mapper, never()).toDTO(any(CardConfiguration.class));
    }

    @Test
    void updateConfiguration_Success() {
        // Arrange
        when(repository.findByCardConfigurationId(configId)).thenReturn(Mono.just(configEntity));
        when(repository.save(any(CardConfiguration.class))).thenReturn(Mono.just(configEntity));
        when(mapper.toDTO(any(CardConfiguration.class))).thenReturn(configDTO);

        // Act & Assert
        StepVerifier.create(service.updateConfiguration(cardId, configId, configDTO))
                .expectNext(configDTO)
                .verifyComplete();

        verify(repository).findByCardConfigurationId(configId);
        verify(repository).save(configEntity);
        verify(mapper).toDTO(configEntity);
    }

    @Test
    void updateConfiguration_NotFound() {
        // Arrange
        when(repository.findByCardConfigurationId(configId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.updateConfiguration(cardId, configId, configDTO))
                .verifyComplete();

        verify(repository).findByCardConfigurationId(configId);
        verify(repository, never()).save(any(CardConfiguration.class));
        verify(mapper, never()).toDTO(any(CardConfiguration.class));
    }

    @Test
    void updateConfiguration_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByCardConfigurationId(configId)).thenReturn(Mono.just(configEntity));

        // Act & Assert
        StepVerifier.create(service.updateConfiguration(wrongCardId, configId, configDTO))
                .verifyComplete();

        verify(repository).findByCardConfigurationId(configId);
        verify(repository, never()).save(any(CardConfiguration.class));
        verify(mapper, never()).toDTO(any(CardConfiguration.class));
    }

    @Test
    void deleteConfiguration_Success() {
        // Arrange
        when(repository.findByCardConfigurationId(configId)).thenReturn(Mono.just(configEntity));
        when(repository.delete(configEntity)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteConfiguration(cardId, configId))
                .verifyComplete();

        verify(repository).findByCardConfigurationId(configId);
        verify(repository).delete(configEntity);
    }

    @Test
    void deleteConfiguration_NotFound() {
        // Arrange
        when(repository.findByCardConfigurationId(configId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteConfiguration(cardId, configId))
                .verifyComplete();

        verify(repository).findByCardConfigurationId(configId);
        verify(repository, never()).delete(any(CardConfiguration.class));
    }

    @Test
    void deleteConfiguration_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByCardConfigurationId(configId)).thenReturn(Mono.just(configEntity));

        // Act & Assert
        StepVerifier.create(service.deleteConfiguration(wrongCardId, configId))
                .verifyComplete();

        verify(repository).findByCardConfigurationId(configId);
        verify(repository, never()).delete(any(CardConfiguration.class));
    }
}
