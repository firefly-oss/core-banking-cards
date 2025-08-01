package com.catalis.core.banking.cards.core.services.interest.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.interest.v1.CardInterestMapper;
import com.catalis.core.banking.cards.interfaces.dtos.interest.v1.CardInterestDTO;
import com.catalis.core.banking.cards.models.entities.interest.v1.CardInterest;
import com.catalis.core.banking.cards.models.repositories.interest.v1.CardInterestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
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
public class CardInterestServiceTest {

    @Mock
    private CardInterestRepository repository;

    @Mock
    private CardInterestMapper mapper;

    @InjectMocks
    private CardInterestServiceImpl service;

    private CardInterestDTO interestDTO;
    private CardInterest interestEntity;
    private final Long cardId = 1L;
    private final Long interestId = 2L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        LocalDateTime now = LocalDateTime.now();
        interestDTO = CardInterestDTO.builder()
                .interestId(interestId)
                .cardId(cardId)
                .interestType("PURCHASE")
                .interestRate(new BigDecimal("18.99"))
                .calculationMethod("DAILY")
                .accrualStartDate(now)
                .build();

        interestEntity = new CardInterest();
        interestEntity.setInterestId(interestId);
        interestEntity.setCardId(cardId);
        interestEntity.setInterestType("PURCHASE");
        interestEntity.setInterestRate(new BigDecimal("18.99"));
        interestEntity.setCalculationMethod("DAILY");
        interestEntity.setAccrualStartDate(now);
    }

    @Test
    void listInterests_Success() {
        // Arrange
        PaginationRequest paginationRequest = new PaginationRequest();

        @SuppressWarnings("unchecked")
        PaginationResponse<CardInterestDTO> expectedResponse = mock(PaginationResponse.class);

        try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
            paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            )).thenReturn(Mono.just(expectedResponse));

            // Act & Assert
            StepVerifier.create(service.listInterests(cardId, paginationRequest))
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
    void createInterest_Success() {
        // Arrange
        when(mapper.toEntity(any(CardInterestDTO.class))).thenReturn(interestEntity);
        when(repository.save(any(CardInterest.class))).thenReturn(Mono.just(interestEntity));
        when(mapper.toDTO(any(CardInterest.class))).thenReturn(interestDTO);

        // Act & Assert
        StepVerifier.create(service.createInterest(cardId, interestDTO))
                .expectNext(interestDTO)
                .verifyComplete();

        verify(mapper).toEntity(interestDTO);
        verify(repository).save(interestEntity);
        verify(mapper).toDTO(interestEntity);
    }

    @Test
    void getInterest_Success() {
        // Arrange
        when(repository.findByInterestId(interestId)).thenReturn(Mono.just(interestEntity));
        when(mapper.toDTO(any(CardInterest.class))).thenReturn(interestDTO);

        // Act & Assert
        StepVerifier.create(service.getInterest(cardId, interestId))
                .expectNext(interestDTO)
                .verifyComplete();

        verify(repository).findByInterestId(interestId);
        verify(mapper).toDTO(interestEntity);
    }

    @Test
    void getInterest_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByInterestId(interestId)).thenReturn(Mono.just(interestEntity));

        // Act & Assert
        StepVerifier.create(service.getInterest(wrongCardId, interestId))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByInterestId(interestId);
        verify(mapper, never()).toDTO(any(CardInterest.class));
    }

    @Test
    void updateInterest_Success() {
        // Arrange
        when(repository.findByInterestId(interestId)).thenReturn(Mono.just(interestEntity));
        when(mapper.toEntity(any(CardInterestDTO.class))).thenReturn(interestEntity);
        when(repository.save(any(CardInterest.class))).thenReturn(Mono.just(interestEntity));
        when(mapper.toDTO(any(CardInterest.class))).thenReturn(interestDTO);

        // Act & Assert
        StepVerifier.create(service.updateInterest(cardId, interestId, interestDTO))
                .expectNext(interestDTO)
                .verifyComplete();

        verify(repository).findByInterestId(interestId);
        verify(mapper).toEntity(interestDTO);
        verify(repository).save(interestEntity);
        verify(mapper).toDTO(interestEntity);
    }

    @Test
    void updateInterest_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByInterestId(interestId)).thenReturn(Mono.just(interestEntity));

        // Act & Assert
        StepVerifier.create(service.updateInterest(wrongCardId, interestId, interestDTO))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByInterestId(interestId);
        verify(repository, never()).save(any(CardInterest.class));
        verify(mapper, never()).toDTO(any(CardInterest.class));
    }

    @Test
    void deleteInterest_Success() {
        // Arrange
        when(repository.findByInterestId(interestId)).thenReturn(Mono.just(interestEntity));
        when(repository.delete(interestEntity)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteInterest(cardId, interestId))
                .verifyComplete();

        verify(repository).findByInterestId(interestId);
        verify(repository).delete(interestEntity);
    }

    @Test
    void deleteInterest_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByInterestId(interestId)).thenReturn(Mono.just(interestEntity));

        // Act & Assert
        StepVerifier.create(service.deleteInterest(wrongCardId, interestId))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByInterestId(interestId);
        verify(repository, never()).delete(any(CardInterest.class));
    }
}
