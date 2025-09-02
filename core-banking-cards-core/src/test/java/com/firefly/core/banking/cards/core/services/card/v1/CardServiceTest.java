package com.firefly.core.banking.cards.core.services.card.v1;

import com.firefly.core.banking.cards.core.mappers.card.v1.CardMapper;
import com.firefly.core.banking.cards.interfaces.dtos.card.v1.CardDTO;
import com.firefly.core.banking.cards.interfaces.enums.card.v1.CardStatusEnum;
import com.firefly.core.banking.cards.models.entities.card.v1.Card;
import com.firefly.core.banking.cards.models.repositories.card.v1.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private CardMapper cardMapper;

    @InjectMocks
    private CardServiceImpl cardService;

    private CardDTO cardDTO;
    private Card card;
    private final UUID cardId = UUID.randomUUID();
    private final UUID contractId = UUID.randomUUID();
    private final UUID accountId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        // Initialize test data
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = now.plusYears(3);

        cardDTO = CardDTO.builder()
                .cardId(cardId)
                .contractId(contractId)
                .accountId(accountId)
                .cardTypeId(UUID.randomUUID())
                .cardStatus(CardStatusEnum.ACTIVE)
                .issuerId(UUID.randomUUID())
                .issuanceDate(now)
                .expirationDate(expirationDate)
                .isPhysical(true)
                .build();

        card = new Card();
        card.setCardId(cardId);
        card.setContractId(contractId);
        card.setAccountId(accountId);
        card.setCardTypeId(UUID.randomUUID());
        card.setCardStatus(CardStatusEnum.ACTIVE);
        card.setIssuerId(UUID.randomUUID());
        card.setIssuanceDate(now);
        card.setExpirationDate(expirationDate);
        card.setIsPhysical(true);
    }

    @Test
    void createCard_Success() {
        // Arrange
        when(cardMapper.toEntity(any(CardDTO.class))).thenReturn(card);
        when(cardRepository.save(any(Card.class))).thenReturn(Mono.just(card));
        when(cardMapper.toDTO(any(Card.class))).thenReturn(cardDTO);

        // Act & Assert
        StepVerifier.create(cardService.createCard(cardDTO))
                .expectNext(cardDTO)
                .verifyComplete();

        verify(cardMapper).toEntity(cardDTO);
        verify(cardRepository).save(card);
        verify(cardMapper).toDTO(card);
    }

    @Test
    void getCard_Success() {
        // Arrange
        when(cardRepository.findByCardId(cardId)).thenReturn(Mono.just(card));
        when(cardMapper.toDTO(any(Card.class))).thenReturn(cardDTO);

        // Act & Assert
        StepVerifier.create(cardService.getCard(cardId))
                .expectNext(cardDTO)
                .verifyComplete();

        verify(cardRepository).findByCardId(cardId);
        verify(cardMapper).toDTO(card);
    }

    @Test
    void getCard_NotFound() {
        // Arrange
        when(cardRepository.findByCardId(cardId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(cardService.getCard(cardId))
                .verifyComplete();

        verify(cardRepository).findByCardId(cardId);
        verify(cardMapper, never()).toDTO(any(Card.class));
    }

    @Test
    void updateCard_Success() {
        // Arrange
        when(cardRepository.findByCardId(cardId)).thenReturn(Mono.just(card));
        when(cardMapper.toEntity(any(CardDTO.class))).thenReturn(card);
        when(cardRepository.save(any(Card.class))).thenReturn(Mono.just(card));
        when(cardMapper.toDTO(any(Card.class))).thenReturn(cardDTO);

        // Act & Assert
        StepVerifier.create(cardService.updateCard(cardId, cardDTO))
                .expectNext(cardDTO)
                .verifyComplete();

        verify(cardRepository).findByCardId(cardId);
        verify(cardMapper).toEntity(cardDTO);
        verify(cardRepository).save(card);
        verify(cardMapper).toDTO(card);
    }

    @Test
    void updateCard_NotFound() {
        // Arrange
        when(cardRepository.findByCardId(cardId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(cardService.updateCard(cardId, cardDTO))
                .verifyComplete();

        verify(cardRepository).findByCardId(cardId);
        verify(cardMapper, never()).toEntity(any(CardDTO.class));
        verify(cardRepository, never()).save(any(Card.class));
        verify(cardMapper, never()).toDTO(any(Card.class));
    }

    @Test
    void deleteCard_Success() {
        // Arrange
        when(cardRepository.findByCardId(cardId)).thenReturn(Mono.just(card));
        when(cardRepository.delete(card)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(cardService.deleteCard(cardId))
                .verifyComplete();

        verify(cardRepository).findByCardId(cardId);
        verify(cardRepository).delete(card);
    }

    @Test
    void deleteCard_NotFound() {
        // Arrange
        when(cardRepository.findByCardId(cardId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(cardService.deleteCard(cardId))
                .verifyComplete();

        verify(cardRepository).findByCardId(cardId);
        verify(cardRepository, never()).delete(any(Card.class));
    }
}
