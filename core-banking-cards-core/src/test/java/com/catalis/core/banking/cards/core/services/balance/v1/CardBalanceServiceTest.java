package com.catalis.core.banking.cards.core.services.balance.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.balance.v1.CardBalanceMapper;
import com.catalis.core.banking.cards.interfaces.dtos.balance.v1.CardBalanceDTO;
import com.catalis.core.banking.cards.models.entities.balance.v1.CardBalance;
import com.catalis.core.banking.cards.models.repositories.balance.v1.CardBalanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardBalanceServiceTest {

    @Mock
    private CardBalanceRepository repository;

    @Mock
    private CardBalanceMapper mapper;

    @InjectMocks
    private CardBalanceServiceImpl service;

    private CardBalanceDTO balanceDTO;
    private CardBalance balanceEntity;
    private final Long cardId = 1L;
    private final Long balanceId = 2L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        balanceDTO = CardBalanceDTO.builder()
                .balanceId(balanceId)
                .cardId(cardId)
                .balanceType("AVAILABLE")
                .amount(new BigDecimal("1000.00"))
                .currencyCode("USD")
                .lastUpdated(LocalDateTime.now())
                .build();

        balanceEntity = new CardBalance();
        balanceEntity.setBalanceId(balanceId);
        balanceEntity.setCardId(cardId);
        balanceEntity.setBalanceType("AVAILABLE");
        balanceEntity.setAmount(new BigDecimal("1000.00"));
        balanceEntity.setCurrencyCode("USD");
        balanceEntity.setLastUpdated(LocalDateTime.now());
    }

    @Test
    void listBalances_Success() {
        // Arrange
        PaginationRequest paginationRequest = new PaginationRequest();

        @SuppressWarnings("unchecked")
        PaginationResponse<CardBalanceDTO> expectedResponse = mock(PaginationResponse.class);

        try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
            paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            )).thenReturn(Mono.just(expectedResponse));

            // Act & Assert
            StepVerifier.create(service.listBalances(cardId, paginationRequest))
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
    void createBalance_Success() {
        // Arrange
        when(mapper.toEntity(any(CardBalanceDTO.class))).thenReturn(balanceEntity);
        when(repository.save(any(CardBalance.class))).thenReturn(Mono.just(balanceEntity));
        when(mapper.toDTO(any(CardBalance.class))).thenReturn(balanceDTO);

        // Act & Assert
        StepVerifier.create(service.createBalance(cardId, balanceDTO))
                .expectNext(balanceDTO)
                .verifyComplete();

        verify(mapper).toEntity(balanceDTO);
        verify(repository).save(balanceEntity);
        verify(mapper).toDTO(balanceEntity);
    }

    @Test
    void getBalance_Success() {
        // Arrange
        when(repository.findByBalanceId(balanceId)).thenReturn(Mono.just(balanceEntity));
        when(mapper.toDTO(any(CardBalance.class))).thenReturn(balanceDTO);

        // Act & Assert
        StepVerifier.create(service.getBalance(cardId, balanceId))
                .expectNext(balanceDTO)
                .verifyComplete();

        verify(repository).findByBalanceId(balanceId);
        verify(mapper).toDTO(balanceEntity);
    }

    @Test
    void getBalance_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByBalanceId(balanceId)).thenReturn(Mono.just(balanceEntity));

        // Act & Assert
        StepVerifier.create(service.getBalance(wrongCardId, balanceId))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByBalanceId(balanceId);
        verify(mapper, never()).toDTO(any(CardBalance.class));
    }

    @Test
    void updateBalance_Success() {
        // Arrange
        when(repository.findByBalanceId(balanceId)).thenReturn(Mono.just(balanceEntity));
        when(mapper.toEntity(any(CardBalanceDTO.class))).thenReturn(balanceEntity);
        when(repository.save(any(CardBalance.class))).thenReturn(Mono.just(balanceEntity));
        when(mapper.toDTO(any(CardBalance.class))).thenReturn(balanceDTO);

        // Act & Assert
        StepVerifier.create(service.updateBalance(cardId, balanceId, balanceDTO))
                .expectNext(balanceDTO)
                .verifyComplete();

        verify(repository).findByBalanceId(balanceId);
        verify(mapper).toEntity(balanceDTO);
        verify(repository).save(balanceEntity);
        verify(mapper).toDTO(balanceEntity);
    }

    @Test
    void updateBalance_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByBalanceId(balanceId)).thenReturn(Mono.just(balanceEntity));

        // Act & Assert
        StepVerifier.create(service.updateBalance(wrongCardId, balanceId, balanceDTO))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByBalanceId(balanceId);
        verify(repository, never()).save(any(CardBalance.class));
        verify(mapper, never()).toDTO(any(CardBalance.class));
    }

    @Test
    void deleteBalance_Success() {
        // Arrange
        when(repository.findByBalanceId(balanceId)).thenReturn(Mono.just(balanceEntity));
        when(repository.delete(balanceEntity)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteBalance(cardId, balanceId))
                .verifyComplete();

        verify(repository).findByBalanceId(balanceId);
        verify(repository).delete(balanceEntity);
    }

    @Test
    void deleteBalance_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByBalanceId(balanceId)).thenReturn(Mono.just(balanceEntity));

        // Act & Assert
        StepVerifier.create(service.deleteBalance(wrongCardId, balanceId))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByBalanceId(balanceId);
        verify(repository, never()).delete(any(CardBalance.class));
    }
}
