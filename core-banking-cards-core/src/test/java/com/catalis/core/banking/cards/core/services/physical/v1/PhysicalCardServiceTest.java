package com.catalis.core.banking.cards.core.services.physical.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.physical.v1.PhysicalCardMapper;
import com.catalis.core.banking.cards.interfaces.dtos.physical.v1.PhysicalCardDTO;
import com.catalis.core.banking.cards.models.entities.physical.v1.PhysicalCard;
import com.catalis.core.banking.cards.models.repositories.physical.v1.PhysicalCardRepository;
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
public class PhysicalCardServiceTest {

    @Mock
    private PhysicalCardRepository repository;

    @Mock
    private PhysicalCardMapper mapper;

    @InjectMocks
    private PhysicalCardServiceImpl service;

    private PhysicalCardDTO physicalCardDTO;
    private PhysicalCard physicalCardEntity;
    private final Long cardId = 1L;
    private final Long physicalCardId = 2L;
    private final LocalDateTime now = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        // Initialize test data
        physicalCardDTO = PhysicalCardDTO.builder()
                .physicalCardId(physicalCardId)
                .cardId(cardId)
                .embossedName("John Doe")
                .plasticId("P12345")
                .designId(1L)
                .isContactless(true)
                .isChip(true)
                .isMagstripe(true)
                .manufacturingStatus("COMPLETED")
                .manufacturingDate(now.minusDays(10))
                .shippingAddress("123 Main St")
                .shippingCity("New York")
                .shippingState("NY")
                .shippingCountry("USA")
                .shippingPostalCode("10001")
                .shippingMethod("Express")
                .shippingTrackingNumber("TRK123456")
                .shippingCarrier("FedEx")
                .shipmentDate(now)
                .estimatedDeliveryDate(now.plusDays(3))
                .actualDeliveryDate(now.plusDays(5))
                .activationMethod("ONLINE")
                .activationDate(now.plusDays(6))
                .isActivated(true)
                .replacementReason(null)
                .previousCardId(null)
                .notes("Test card")
                .build();

        physicalCardEntity = new PhysicalCard();
        physicalCardEntity.setPhysicalCardId(physicalCardId);
        physicalCardEntity.setCardId(cardId);
        physicalCardEntity.setEmbossedName("John Doe");
        physicalCardEntity.setPlasticId("P12345");
        physicalCardEntity.setDesignId(1L);
        physicalCardEntity.setIsContactless(true);
        physicalCardEntity.setIsChip(true);
        physicalCardEntity.setIsMagstripe(true);
        physicalCardEntity.setManufacturingStatus("COMPLETED");
        physicalCardEntity.setManufacturingDate(now.minusDays(10));
        physicalCardEntity.setShippingAddress("123 Main St");
        physicalCardEntity.setShippingCity("New York");
        physicalCardEntity.setShippingState("NY");
        physicalCardEntity.setShippingCountry("USA");
        physicalCardEntity.setShippingPostalCode("10001");
        physicalCardEntity.setShippingMethod("Express");
        physicalCardEntity.setShippingTrackingNumber("TRK123456");
        physicalCardEntity.setShippingCarrier("FedEx");
        physicalCardEntity.setShipmentDate(now);
        physicalCardEntity.setEstimatedDeliveryDate(now.plusDays(3));
        physicalCardEntity.setActualDeliveryDate(now.plusDays(5));
        physicalCardEntity.setActivationMethod("ONLINE");
        physicalCardEntity.setActivationDate(now.plusDays(6));
        physicalCardEntity.setIsActivated(true);
        physicalCardEntity.setReplacementReason(null);
        physicalCardEntity.setPreviousCardId(null);
        physicalCardEntity.setNotes("Test card");
    }

    @Test
    void listPhysicalCards_Success() {
        // Arrange
        PaginationRequest paginationRequest = new PaginationRequest();

        @SuppressWarnings("unchecked")
        PaginationResponse<PhysicalCardDTO> expectedResponse = mock(PaginationResponse.class);

        try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
            paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            )).thenReturn(Mono.just(expectedResponse));

            // Act & Assert
            StepVerifier.create(service.listPhysicalCards(cardId, paginationRequest))
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
    void createPhysicalCard_Success() {
        // Arrange
        when(mapper.toEntity(any(PhysicalCardDTO.class))).thenReturn(physicalCardEntity);
        when(repository.save(any(PhysicalCard.class))).thenReturn(Mono.just(physicalCardEntity));
        when(mapper.toDTO(any(PhysicalCard.class))).thenReturn(physicalCardDTO);

        // Act & Assert
        StepVerifier.create(service.createPhysicalCard(cardId, physicalCardDTO))
                .expectNext(physicalCardDTO)
                .verifyComplete();

        verify(mapper).toEntity(physicalCardDTO);
        verify(repository).save(physicalCardEntity);
        verify(mapper).toDTO(physicalCardEntity);
    }

    @Test
    void getPhysicalCard_Success() {
        // Arrange
        when(repository.findByPhysicalCardId(physicalCardId)).thenReturn(Mono.just(physicalCardEntity));
        when(mapper.toDTO(any(PhysicalCard.class))).thenReturn(physicalCardDTO);

        // Act & Assert
        StepVerifier.create(service.getPhysicalCard(cardId, physicalCardId))
                .expectNext(physicalCardDTO)
                .verifyComplete();

        verify(repository).findByPhysicalCardId(physicalCardId);
        verify(mapper).toDTO(physicalCardEntity);
    }

    @Test
    void getPhysicalCard_NotFound() {
        // Arrange
        when(repository.findByPhysicalCardId(physicalCardId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.getPhysicalCard(cardId, physicalCardId))
                .verifyComplete();

        verify(repository).findByPhysicalCardId(physicalCardId);
        verify(mapper, never()).toDTO(any(PhysicalCard.class));
    }

    @Test
    void getPhysicalCard_WrongCard() {
        // Arrange
        PhysicalCard wrongCardPhysicalCard = new PhysicalCard();
        wrongCardPhysicalCard.setPhysicalCardId(physicalCardId);
        wrongCardPhysicalCard.setCardId(999L); // Different card ID

        when(repository.findByPhysicalCardId(physicalCardId)).thenReturn(Mono.just(wrongCardPhysicalCard));

        // Act & Assert
        StepVerifier.create(service.getPhysicalCard(cardId, physicalCardId))
                .verifyComplete();

        verify(repository).findByPhysicalCardId(physicalCardId);
        verify(mapper, never()).toDTO(any(PhysicalCard.class));
    }

    @Test
    void updatePhysicalCard_Success() {
        // Arrange
        when(repository.findByPhysicalCardId(physicalCardId)).thenReturn(Mono.just(physicalCardEntity));
        when(mapper.toEntity(any(PhysicalCardDTO.class))).thenReturn(physicalCardEntity);
        when(repository.save(any(PhysicalCard.class))).thenReturn(Mono.just(physicalCardEntity));
        when(mapper.toDTO(any(PhysicalCard.class))).thenReturn(physicalCardDTO);

        // Act & Assert
        StepVerifier.create(service.updatePhysicalCard(cardId, physicalCardId, physicalCardDTO))
                .expectNext(physicalCardDTO)
                .verifyComplete();

        verify(repository).findByPhysicalCardId(physicalCardId);
        verify(mapper).toEntity(physicalCardDTO);
        verify(repository).save(physicalCardEntity);
        verify(mapper).toDTO(physicalCardEntity);
    }

    @Test
    void updatePhysicalCard_NotFound() {
        // Arrange
        when(repository.findByPhysicalCardId(physicalCardId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.updatePhysicalCard(cardId, physicalCardId, physicalCardDTO))
                .verifyComplete();

        verify(repository).findByPhysicalCardId(physicalCardId);
        verify(mapper, never()).toEntity(any(PhysicalCardDTO.class));
        verify(repository, never()).save(any(PhysicalCard.class));
        verify(mapper, never()).toDTO(any(PhysicalCard.class));
    }

    @Test
    void updatePhysicalCard_WrongCard() {
        // Arrange
        PhysicalCard wrongCardPhysicalCard = new PhysicalCard();
        wrongCardPhysicalCard.setPhysicalCardId(physicalCardId);
        wrongCardPhysicalCard.setCardId(999L); // Different card ID

        when(repository.findByPhysicalCardId(physicalCardId)).thenReturn(Mono.just(wrongCardPhysicalCard));

        // Act & Assert
        StepVerifier.create(service.updatePhysicalCard(cardId, physicalCardId, physicalCardDTO))
                .verifyComplete();

        verify(repository).findByPhysicalCardId(physicalCardId);
        verify(mapper, never()).toEntity(any(PhysicalCardDTO.class));
        verify(repository, never()).save(any(PhysicalCard.class));
        verify(mapper, never()).toDTO(any(PhysicalCard.class));
    }

    @Test
    void deletePhysicalCard_Success() {
        // Arrange
        when(repository.findByPhysicalCardId(physicalCardId)).thenReturn(Mono.just(physicalCardEntity));
        when(repository.delete(physicalCardEntity)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deletePhysicalCard(cardId, physicalCardId))
                .verifyComplete();

        verify(repository).findByPhysicalCardId(physicalCardId);
        verify(repository).delete(physicalCardEntity);
    }

    @Test
    void deletePhysicalCard_NotFound() {
        // Arrange
        when(repository.findByPhysicalCardId(physicalCardId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deletePhysicalCard(cardId, physicalCardId))
                .verifyComplete();

        verify(repository).findByPhysicalCardId(physicalCardId);
        verify(repository, never()).delete(any(PhysicalCard.class));
    }

    @Test
    void deletePhysicalCard_WrongCard() {
        // Arrange
        PhysicalCard wrongCardPhysicalCard = new PhysicalCard();
        wrongCardPhysicalCard.setPhysicalCardId(physicalCardId);
        wrongCardPhysicalCard.setCardId(999L); // Different card ID

        when(repository.findByPhysicalCardId(physicalCardId)).thenReturn(Mono.just(wrongCardPhysicalCard));

        // Act & Assert
        StepVerifier.create(service.deletePhysicalCard(cardId, physicalCardId))
                .verifyComplete();

        verify(repository).findByPhysicalCardId(physicalCardId);
        verify(repository, never()).delete(any(PhysicalCard.class));
    }
}