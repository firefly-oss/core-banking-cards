package com.firefly.core.banking.cards.core.services.promotion.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.promotion.v1.CardPromotionMapper;
import com.firefly.core.banking.cards.interfaces.dtos.promotion.v1.CardPromotionDTO;
import com.firefly.core.banking.cards.models.entities.promotion.v1.CardPromotion;
import com.firefly.core.banking.cards.models.repositories.promotion.v1.CardPromotionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardPromotionServiceTest {

    @Mock
    private CardPromotionRepository repository;

    @Mock
    private CardPromotionMapper mapper;

    @InjectMocks
    private CardPromotionServiceImpl service;

    private CardPromotionDTO promotionDTO;
    private CardPromotion promotionEntity;
    private final Long cardId = 1L;
    private final Long promotionId = 2L;
    private final Long programId = 3L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        promotionDTO = CardPromotionDTO.builder()
                .promotionId(promotionId)
                .cardId(cardId)
                .programId(programId)
                .promotionName("Cashback Offer")
                .promotionCode("CASH10")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusMonths(3))
                .build();

        promotionEntity = new CardPromotion();
        promotionEntity.setPromotionId(promotionId);
        promotionEntity.setCardId(cardId);
        promotionEntity.setProgramId(programId);
        promotionEntity.setPromotionName("Cashback Offer");
        promotionEntity.setPromotionCode("CASH10");
        promotionEntity.setStartDate(LocalDateTime.now());
        promotionEntity.setEndDate(LocalDateTime.now().plusMonths(3));
    }

    @Test
    void listPromotions_Success() {
        // Arrange
        PaginationRequest paginationRequest = new PaginationRequest();

        @SuppressWarnings("unchecked")
        PaginationResponse<CardPromotionDTO> expectedResponse = mock(PaginationResponse.class);

        try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
            paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            )).thenReturn(Mono.just(expectedResponse));

            // Act & Assert
            StepVerifier.create(service.listPromotions(cardId, paginationRequest))
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
    void createPromotion_Success() {
        // Arrange
        when(mapper.toEntity(any(CardPromotionDTO.class))).thenReturn(promotionEntity);
        when(repository.save(any(CardPromotion.class))).thenReturn(Mono.just(promotionEntity));
        when(mapper.toDTO(any(CardPromotion.class))).thenReturn(promotionDTO);

        // Act & Assert
        StepVerifier.create(service.createPromotion(cardId, promotionDTO))
                .expectNext(promotionDTO)
                .verifyComplete();

        verify(mapper).toEntity(promotionDTO);
        verify(repository).save(promotionEntity);
        verify(mapper).toDTO(promotionEntity);
    }

    @Test
    void getPromotion_Success() {
        // Arrange
        when(repository.findByPromotionId(promotionId)).thenReturn(Mono.just(promotionEntity));
        when(mapper.toDTO(any(CardPromotion.class))).thenReturn(promotionDTO);

        // Act & Assert
        StepVerifier.create(service.getPromotion(cardId, promotionId))
                .expectNext(promotionDTO)
                .verifyComplete();

        verify(repository).findByPromotionId(promotionId);
        verify(mapper).toDTO(promotionEntity);
    }

    @Test
    void getPromotion_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByPromotionId(promotionId)).thenReturn(Mono.just(promotionEntity));

        // Act & Assert
        StepVerifier.create(service.getPromotion(wrongCardId, promotionId))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByPromotionId(promotionId);
        verify(mapper, never()).toDTO(any(CardPromotion.class));
    }

    @Test
    void updatePromotion_Success() {
        // Arrange
        when(repository.findByPromotionId(promotionId)).thenReturn(Mono.just(promotionEntity));
        when(mapper.toEntity(any(CardPromotionDTO.class))).thenReturn(promotionEntity);
        when(repository.save(any(CardPromotion.class))).thenReturn(Mono.just(promotionEntity));
        when(mapper.toDTO(any(CardPromotion.class))).thenReturn(promotionDTO);

        // Act & Assert
        StepVerifier.create(service.updatePromotion(cardId, promotionId, promotionDTO))
                .expectNext(promotionDTO)
                .verifyComplete();

        verify(repository).findByPromotionId(promotionId);
        verify(mapper).toEntity(promotionDTO);
        verify(repository).save(promotionEntity);
        verify(mapper).toDTO(promotionEntity);
    }

    @Test
    void updatePromotion_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByPromotionId(promotionId)).thenReturn(Mono.just(promotionEntity));

        // Act & Assert
        StepVerifier.create(service.updatePromotion(wrongCardId, promotionId, promotionDTO))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByPromotionId(promotionId);
        verify(repository, never()).save(any(CardPromotion.class));
        verify(mapper, never()).toDTO(any(CardPromotion.class));
    }

    @Test
    void deletePromotion_Success() {
        // Arrange
        when(repository.findByPromotionId(promotionId)).thenReturn(Mono.just(promotionEntity));
        when(repository.delete(promotionEntity)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deletePromotion(cardId, promotionId))
                .verifyComplete();

        verify(repository).findByPromotionId(promotionId);
        verify(repository).delete(promotionEntity);
    }

    @Test
    void deletePromotion_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByPromotionId(promotionId)).thenReturn(Mono.just(promotionEntity));

        // Act & Assert
        StepVerifier.create(service.deletePromotion(wrongCardId, promotionId))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByPromotionId(promotionId);
        verify(repository, never()).delete(any(CardPromotion.class));
    }
}
