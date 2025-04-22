package com.catalis.core.banking.cards.core.services.virtual.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.virtual.v1.VirtualCardMapper;
import com.catalis.core.banking.cards.interfaces.dtos.virtual.v1.VirtualCardDTO;
import com.catalis.core.banking.cards.interfaces.enums.virtual.v1.VirtualCardStatusEnum;
import com.catalis.core.banking.cards.models.entities.virtual.v1.VirtualCard;
import com.catalis.core.banking.cards.models.repositories.virtual.v1.VirtualCardRepository;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VirtualCardServiceTest {

    @Mock
    private VirtualCardRepository repository;

    @Mock
    private VirtualCardMapper mapper;

    @InjectMocks
    private VirtualCardServiceImpl service;

    private VirtualCardDTO virtualCardDTO;
    private VirtualCard virtualCardEntity;
    private final Long cardId = 1L;
    private final Long virtualCardId = 2L;
    private final LocalDateTime now = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        // Initialize test data
        virtualCardDTO = VirtualCardDTO.builder()
                .virtualCardId(virtualCardId)
                .cardId(cardId)
                .deviceId("DEVICE123")
                .virtualCardNumber("1234567890123456")
                .virtualCardStatus(VirtualCardStatusEnum.ACTIVE)
                .creationTimestamp(now)
                .updateTimestamp(now)
                .build();

        virtualCardEntity = new VirtualCard();
        virtualCardEntity.setVirtualCardId(virtualCardId);
        virtualCardEntity.setCardId(cardId);
        virtualCardEntity.setDeviceId("DEVICE123");
        virtualCardEntity.setVirtualCardNumber("1234567890123456");
        virtualCardEntity.setVirtualCardStatus(VirtualCardStatusEnum.ACTIVE);
        virtualCardEntity.setCreationTimestamp(now);
        virtualCardEntity.setUpdateTimestamp(now);
    }

    @Test
    void listVirtualCards_Success() {
        // Arrange
        PaginationRequest paginationRequest = new PaginationRequest();
        
        @SuppressWarnings("unchecked")
        PaginationResponse<VirtualCardDTO> expectedResponse = mock(PaginationResponse.class);
        
        try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
            paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            )).thenReturn(Mono.just(expectedResponse));

            // Act & Assert
            StepVerifier.create(service.listVirtualCards(cardId, paginationRequest))
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
    void createVirtualCard_Success() {
        // Arrange
        when(mapper.toEntity(any(VirtualCardDTO.class))).thenReturn(virtualCardEntity);
        when(repository.save(any(VirtualCard.class))).thenReturn(Mono.just(virtualCardEntity));
        when(mapper.toDTO(any(VirtualCard.class))).thenReturn(virtualCardDTO);

        // Act & Assert
        StepVerifier.create(service.createVirtualCard(cardId, virtualCardDTO))
                .expectNext(virtualCardDTO)
                .verifyComplete();

        verify(mapper).toEntity(virtualCardDTO);
        verify(repository).save(virtualCardEntity);
        verify(mapper).toDTO(virtualCardEntity);
    }

    @Test
    void getVirtualCard_Success() {
        // Arrange
        when(repository.findByVirtualCardId(virtualCardId)).thenReturn(Mono.just(virtualCardEntity));
        when(mapper.toDTO(any(VirtualCard.class))).thenReturn(virtualCardDTO);

        // Act & Assert
        StepVerifier.create(service.getVirtualCard(cardId, virtualCardId))
                .expectNext(virtualCardDTO)
                .verifyComplete();

        verify(repository).findByVirtualCardId(virtualCardId);
        verify(mapper).toDTO(virtualCardEntity);
    }

    @Test
    void getVirtualCard_NotFound() {
        // Arrange
        when(repository.findByVirtualCardId(virtualCardId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.getVirtualCard(cardId, virtualCardId))
                .verifyComplete();

        verify(repository).findByVirtualCardId(virtualCardId);
        verify(mapper, never()).toDTO(any(VirtualCard.class));
    }

    @Test
    void getVirtualCard_WrongCard() {
        // Arrange
        VirtualCard wrongCardVirtualCard = new VirtualCard();
        wrongCardVirtualCard.setVirtualCardId(virtualCardId);
        wrongCardVirtualCard.setCardId(999L); // Different card ID
        
        when(repository.findByVirtualCardId(virtualCardId)).thenReturn(Mono.just(wrongCardVirtualCard));

        // Act & Assert
        StepVerifier.create(service.getVirtualCard(cardId, virtualCardId))
                .verifyComplete();

        verify(repository).findByVirtualCardId(virtualCardId);
        verify(mapper, never()).toDTO(any(VirtualCard.class));
    }

    @Test
    void updateVirtualCard_Success() {
        // Arrange
        when(repository.findByVirtualCardId(virtualCardId)).thenReturn(Mono.just(virtualCardEntity));
        when(mapper.toEntity(any(VirtualCardDTO.class))).thenReturn(virtualCardEntity);
        when(repository.save(any(VirtualCard.class))).thenReturn(Mono.just(virtualCardEntity));
        when(mapper.toDTO(any(VirtualCard.class))).thenReturn(virtualCardDTO);

        // Act & Assert
        StepVerifier.create(service.updateVirtualCard(cardId, virtualCardId, virtualCardDTO))
                .expectNext(virtualCardDTO)
                .verifyComplete();

        verify(repository).findByVirtualCardId(virtualCardId);
        verify(mapper).toEntity(virtualCardDTO);
        verify(repository).save(virtualCardEntity);
        verify(mapper).toDTO(virtualCardEntity);
    }

    @Test
    void updateVirtualCard_NotFound() {
        // Arrange
        when(repository.findByVirtualCardId(virtualCardId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.updateVirtualCard(cardId, virtualCardId, virtualCardDTO))
                .verifyComplete();

        verify(repository).findByVirtualCardId(virtualCardId);
        verify(mapper, never()).toEntity(any(VirtualCardDTO.class));
        verify(repository, never()).save(any(VirtualCard.class));
        verify(mapper, never()).toDTO(any(VirtualCard.class));
    }

    @Test
    void updateVirtualCard_WrongCard() {
        // Arrange
        VirtualCard wrongCardVirtualCard = new VirtualCard();
        wrongCardVirtualCard.setVirtualCardId(virtualCardId);
        wrongCardVirtualCard.setCardId(999L); // Different card ID
        
        when(repository.findByVirtualCardId(virtualCardId)).thenReturn(Mono.just(wrongCardVirtualCard));

        // Act & Assert
        StepVerifier.create(service.updateVirtualCard(cardId, virtualCardId, virtualCardDTO))
                .verifyComplete();

        verify(repository).findByVirtualCardId(virtualCardId);
        verify(mapper, never()).toEntity(any(VirtualCardDTO.class));
        verify(repository, never()).save(any(VirtualCard.class));
        verify(mapper, never()).toDTO(any(VirtualCard.class));
    }

    @Test
    void deleteVirtualCard_Success() {
        // Arrange
        when(repository.findByVirtualCardId(virtualCardId)).thenReturn(Mono.just(virtualCardEntity));
        when(repository.delete(virtualCardEntity)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteVirtualCard(cardId, virtualCardId))
                .verifyComplete();

        verify(repository).findByVirtualCardId(virtualCardId);
        verify(repository).delete(virtualCardEntity);
    }

    @Test
    void deleteVirtualCard_NotFound() {
        // Arrange
        when(repository.findByVirtualCardId(virtualCardId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteVirtualCard(cardId, virtualCardId))
                .verifyComplete();

        verify(repository).findByVirtualCardId(virtualCardId);
        verify(repository, never()).delete(any(VirtualCard.class));
    }

    @Test
    void deleteVirtualCard_WrongCard() {
        // Arrange
        VirtualCard wrongCardVirtualCard = new VirtualCard();
        wrongCardVirtualCard.setVirtualCardId(virtualCardId);
        wrongCardVirtualCard.setCardId(999L); // Different card ID
        
        when(repository.findByVirtualCardId(virtualCardId)).thenReturn(Mono.just(wrongCardVirtualCard));

        // Act & Assert
        StepVerifier.create(service.deleteVirtualCard(cardId, virtualCardId))
                .verifyComplete();

        verify(repository).findByVirtualCardId(virtualCardId);
        verify(repository, never()).delete(any(VirtualCard.class));
    }
}