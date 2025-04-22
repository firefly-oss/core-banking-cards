package com.catalis.core.banking.cards.core.services.provider.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.provider.v1.CardProviderMapper;
import com.catalis.core.banking.cards.interfaces.dtos.provider.v1.CardProviderDTO;
import com.catalis.core.banking.cards.interfaces.enums.provider.v1.ProviderStatusEnum;
import com.catalis.core.banking.cards.models.entities.provider.v1.CardProvider;
import com.catalis.core.banking.cards.models.repositories.provider.v1.CardProviderRepository;
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
public class CardProviderServiceTest {

    @Mock
    private CardProviderRepository repository;

    @Mock
    private CardProviderMapper mapper;

    @InjectMocks
    private CardProviderServiceImpl service;

    private CardProviderDTO providerDTO;
    private CardProvider providerEntity;
    private final Long cardId = 1L;
    private final Long providerId = 2L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        providerDTO = CardProviderDTO.builder()
                .cardProviderId(providerId)
                .cardId(cardId)
                .providerName("Test Provider")
                .externalReference("EXT-REF-123")
                .status(ProviderStatusEnum.ACTIVE)
                .build();

        providerEntity = new CardProvider();
        providerEntity.setCardProviderId(providerId);
        providerEntity.setCardId(cardId);
        providerEntity.setProviderName("Test Provider");
        providerEntity.setExternalReference("EXT-REF-123");
        providerEntity.setStatus(ProviderStatusEnum.ACTIVE);
    }

    @Test
    void listProviders_Success() {
        // Arrange
        PaginationRequest paginationRequest = new PaginationRequest();
        
        @SuppressWarnings("unchecked")
        PaginationResponse<CardProviderDTO> expectedResponse = mock(PaginationResponse.class);
        
        try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
            paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            )).thenReturn(Mono.just(expectedResponse));

            // Act & Assert
            StepVerifier.create(service.listProviders(cardId, paginationRequest))
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
    void createProvider_Success() {
        // Arrange
        when(mapper.toEntity(any(CardProviderDTO.class))).thenReturn(providerEntity);
        when(repository.save(any(CardProvider.class))).thenReturn(Mono.just(providerEntity));
        when(mapper.toDTO(any(CardProvider.class))).thenReturn(providerDTO);

        // Act & Assert
        StepVerifier.create(service.createProvider(cardId, providerDTO))
                .expectNext(providerDTO)
                .verifyComplete();

        verify(mapper).toEntity(providerDTO);
        verify(repository).save(providerEntity);
        verify(mapper).toDTO(providerEntity);
    }

    @Test
    void getProvider_Success() {
        // Arrange
        when(repository.findById(providerId)).thenReturn(Mono.just(providerEntity));
        when(mapper.toDTO(any(CardProvider.class))).thenReturn(providerDTO);

        // Act & Assert
        StepVerifier.create(service.getProvider(cardId, providerId))
                .expectNext(providerDTO)
                .verifyComplete();

        verify(repository).findById(providerId);
        verify(mapper).toDTO(providerEntity);
    }

    @Test
    void getProvider_NotFound() {
        // Arrange
        when(repository.findById(providerId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.getProvider(cardId, providerId))
                .verifyComplete();

        verify(repository).findById(providerId);
        verify(mapper, never()).toDTO(any(CardProvider.class));
    }

    @Test
    void getProvider_WrongCard() {
        // Arrange
        CardProvider wrongCardProvider = new CardProvider();
        wrongCardProvider.setCardProviderId(providerId);
        wrongCardProvider.setCardId(999L); // Different card ID
        
        when(repository.findById(providerId)).thenReturn(Mono.just(wrongCardProvider));

        // Act & Assert
        StepVerifier.create(service.getProvider(cardId, providerId))
                .verifyComplete();

        verify(repository).findById(providerId);
        verify(mapper, never()).toDTO(any(CardProvider.class));
    }

    @Test
    void updateProvider_Success() {
        // Arrange
        when(repository.findById(providerId)).thenReturn(Mono.just(providerEntity));
        when(mapper.toEntity(any(CardProviderDTO.class))).thenReturn(providerEntity);
        when(repository.save(any(CardProvider.class))).thenReturn(Mono.just(providerEntity));
        when(mapper.toDTO(any(CardProvider.class))).thenReturn(providerDTO);

        // Act & Assert
        StepVerifier.create(service.updateProvider(cardId, providerId, providerDTO))
                .expectNext(providerDTO)
                .verifyComplete();

        verify(repository).findById(providerId);
        verify(mapper).toEntity(providerDTO);
        verify(repository).save(providerEntity);
        verify(mapper).toDTO(providerEntity);
    }

    @Test
    void updateProvider_NotFound() {
        // Arrange
        when(repository.findById(providerId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.updateProvider(cardId, providerId, providerDTO))
                .verifyComplete();

        verify(repository).findById(providerId);
        verify(mapper, never()).toEntity(any(CardProviderDTO.class));
        verify(repository, never()).save(any(CardProvider.class));
        verify(mapper, never()).toDTO(any(CardProvider.class));
    }

    @Test
    void updateProvider_WrongCard() {
        // Arrange
        CardProvider wrongCardProvider = new CardProvider();
        wrongCardProvider.setCardProviderId(providerId);
        wrongCardProvider.setCardId(999L); // Different card ID
        
        when(repository.findById(providerId)).thenReturn(Mono.just(wrongCardProvider));

        // Act & Assert
        StepVerifier.create(service.updateProvider(cardId, providerId, providerDTO))
                .verifyComplete();

        verify(repository).findById(providerId);
        verify(mapper, never()).toEntity(any(CardProviderDTO.class));
        verify(repository, never()).save(any(CardProvider.class));
        verify(mapper, never()).toDTO(any(CardProvider.class));
    }

    @Test
    void deleteProvider_Success() {
        // Arrange
        when(repository.findById(providerId)).thenReturn(Mono.just(providerEntity));
        when(repository.delete(providerEntity)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteProvider(cardId, providerId))
                .verifyComplete();

        verify(repository).findById(providerId);
        verify(repository).delete(providerEntity);
    }

    @Test
    void deleteProvider_NotFound() {
        // Arrange
        when(repository.findById(providerId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteProvider(cardId, providerId))
                .verifyComplete();

        verify(repository).findById(providerId);
        verify(repository, never()).delete(any(CardProvider.class));
    }

    @Test
    void deleteProvider_WrongCard() {
        // Arrange
        CardProvider wrongCardProvider = new CardProvider();
        wrongCardProvider.setCardProviderId(providerId);
        wrongCardProvider.setCardId(999L); // Different card ID
        
        when(repository.findById(providerId)).thenReturn(Mono.just(wrongCardProvider));

        // Act & Assert
        StepVerifier.create(service.deleteProvider(cardId, providerId))
                .verifyComplete();

        verify(repository).findById(providerId);
        verify(repository, never()).delete(any(CardProvider.class));
    }
}