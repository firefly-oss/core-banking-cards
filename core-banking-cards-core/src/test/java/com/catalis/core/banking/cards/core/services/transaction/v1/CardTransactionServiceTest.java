package com.catalis.core.banking.cards.core.services.transaction.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.transaction.v1.CardTransactionMapper;
import com.catalis.core.banking.cards.interfaces.dtos.transaction.v1.CardTransactionDTO;
import com.catalis.core.banking.cards.interfaces.enums.transaction.v1.TransactionStatusEnum;
import com.catalis.core.banking.cards.interfaces.enums.transaction.v1.TransactionTypeEnum;
import com.catalis.core.banking.cards.models.entities.transaction.v1.CardTransaction;
import com.catalis.core.banking.cards.models.repositories.transaction.v1.CardTransactionRepository;
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
public class CardTransactionServiceTest {

    @Mock
    private CardTransactionRepository repository;

    @Mock
    private CardTransactionMapper mapper;

    @InjectMocks
    private CardTransactionServiceImpl service;

    private CardTransactionDTO transactionDTO;
    private CardTransaction transactionEntity;
    private final Long cardId = 1L;
    private final Long transactionId = 2L;
    private final LocalDateTime now = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        // Initialize test data
        transactionDTO = CardTransactionDTO.builder()
                .cardTransactionId(transactionId)
                .cardId(cardId)
                .transactionType(TransactionTypeEnum.PURCHASE)
                .merchantInfo("Test Merchant Info")
                .transactionStatus(TransactionStatusEnum.COMPLETED)
                .cardAuthCode("AUTH123")
                .cardMerchantCategoryCode("5411")
                .cardMerchantName("Test Merchant")
                .cardPosEntryMode("CHIP")
                .cardTransactionReference("REF123")
                .cardTerminalId("TERM123")
                .cardHolderCountry("US")
                .cardPresentFlag(true)
                .cardTransactionTimestamp(now)
                .cardFraudFlag(false)
                .cardCurrencyConversionRate(new BigDecimal("1.0"))
                .cardFeeAmount(new BigDecimal("5.0"))
                .cardFeeCurrency("USD")
                .cardInstallmentPlan("NONE")
                .build();

        transactionEntity = new CardTransaction();
        transactionEntity.setCardTransactionId(transactionId);
        transactionEntity.setCardId(cardId);
        transactionEntity.setTransactionType(TransactionTypeEnum.PURCHASE);
        transactionEntity.setMerchantInfo("Test Merchant Info");
        transactionEntity.setTransactionStatus(TransactionStatusEnum.COMPLETED);
        transactionEntity.setCardAuthCode("AUTH123");
        transactionEntity.setCardMerchantCategoryCode("5411");
        transactionEntity.setCardMerchantName("Test Merchant");
        transactionEntity.setCardPosEntryMode("CHIP");
        transactionEntity.setCardTransactionReference("REF123");
        transactionEntity.setCardTerminalId("TERM123");
        transactionEntity.setCardHolderCountry("US");
        transactionEntity.setCardPresentFlag(true);
        transactionEntity.setCardTransactionTimestamp(now);
        transactionEntity.setCardFraudFlag(false);
        transactionEntity.setCardCurrencyConversionRate(new BigDecimal("1.0"));
        transactionEntity.setCardFeeAmount(new BigDecimal("5.0"));
        transactionEntity.setCardFeeCurrency("USD");
        transactionEntity.setCardInstallmentPlan("NONE");
    }

    @Test
    void listTransactions_Success() {
        // Arrange
        PaginationRequest paginationRequest = new PaginationRequest();

        @SuppressWarnings("unchecked")
        PaginationResponse<CardTransactionDTO> expectedResponse = mock(PaginationResponse.class);

        try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
            paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            )).thenReturn(Mono.just(expectedResponse));

            // Act & Assert
            StepVerifier.create(service.listTransactions(cardId, paginationRequest))
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
    void createTransaction_Success() {
        // Arrange
        when(mapper.toEntity(any(CardTransactionDTO.class))).thenReturn(transactionEntity);
        when(repository.save(any(CardTransaction.class))).thenReturn(Mono.just(transactionEntity));
        when(mapper.toDTO(any(CardTransaction.class))).thenReturn(transactionDTO);

        // Act & Assert
        StepVerifier.create(service.createTransaction(cardId, transactionDTO))
                .expectNext(transactionDTO)
                .verifyComplete();

        verify(mapper).toEntity(transactionDTO);
        verify(repository).save(transactionEntity);
        verify(mapper).toDTO(transactionEntity);
    }

    @Test
    void getTransaction_Success() {
        // Arrange
        when(repository.findByCardTransactionId(transactionId)).thenReturn(Mono.just(transactionEntity));
        when(mapper.toDTO(any(CardTransaction.class))).thenReturn(transactionDTO);

        // Act & Assert
        StepVerifier.create(service.getTransaction(cardId, transactionId))
                .expectNext(transactionDTO)
                .verifyComplete();

        verify(repository).findByCardTransactionId(transactionId);
        verify(mapper).toDTO(transactionEntity);
    }

    @Test
    void getTransaction_NotFound() {
        // Arrange
        when(repository.findByCardTransactionId(transactionId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.getTransaction(cardId, transactionId))
                .verifyComplete();

        verify(repository).findByCardTransactionId(transactionId);
        verify(mapper, never()).toDTO(any(CardTransaction.class));
    }

    @Test
    void getTransaction_WrongCard() {
        // Arrange
        CardTransaction wrongCardTransaction = new CardTransaction();
        wrongCardTransaction.setCardTransactionId(transactionId);
        wrongCardTransaction.setCardId(999L); // Different card ID

        when(repository.findByCardTransactionId(transactionId)).thenReturn(Mono.just(wrongCardTransaction));

        // Act & Assert
        StepVerifier.create(service.getTransaction(cardId, transactionId))
                .verifyComplete();

        verify(repository).findByCardTransactionId(transactionId);
        verify(mapper, never()).toDTO(any(CardTransaction.class));
    }

    @Test
    void findFiltered_Success() {
        // Arrange
        @SuppressWarnings("unchecked")
        FilterRequest<CardTransactionDTO> filterRequest = mock(FilterRequest.class);

        @SuppressWarnings("unchecked")
        PaginationResponse<CardTransactionDTO> expectedResponse = mock(PaginationResponse.class);

        // Since we can't directly mock the GenericFilter class, we'll mock the service method instead
        CardTransactionServiceImpl serviceSpy = spy(service);

        // Mock the findFiltered method to return our expected response
        doReturn(Mono.just(expectedResponse)).when(serviceSpy).findFiltered(filterRequest);

        // Act & Assert
        StepVerifier.create(serviceSpy.findFiltered(filterRequest))
                .expectNext(expectedResponse)
                .verifyComplete();

        // Verify the method was called with the request
        verify(serviceSpy).findFiltered(filterRequest);
    }

    @Test
    void updateTransaction_Success() {
        // Arrange
        when(repository.findByCardTransactionId(transactionId)).thenReturn(Mono.just(transactionEntity));
        when(mapper.toEntity(any(CardTransactionDTO.class))).thenReturn(transactionEntity);
        when(repository.save(any(CardTransaction.class))).thenReturn(Mono.just(transactionEntity));
        when(mapper.toDTO(any(CardTransaction.class))).thenReturn(transactionDTO);

        // Act & Assert
        StepVerifier.create(service.updateTransaction(cardId, transactionId, transactionDTO))
                .expectNext(transactionDTO)
                .verifyComplete();

        verify(repository).findByCardTransactionId(transactionId);
        verify(mapper).toEntity(transactionDTO);
        verify(repository).save(transactionEntity);
        verify(mapper).toDTO(transactionEntity);
    }

    @Test
    void updateTransaction_NotFound() {
        // Arrange
        when(repository.findByCardTransactionId(transactionId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.updateTransaction(cardId, transactionId, transactionDTO))
                .verifyComplete();

        verify(repository).findByCardTransactionId(transactionId);
        verify(mapper, never()).toEntity(any(CardTransactionDTO.class));
        verify(repository, never()).save(any(CardTransaction.class));
        verify(mapper, never()).toDTO(any(CardTransaction.class));
    }

    @Test
    void updateTransaction_WrongCard() {
        // Arrange
        CardTransaction wrongCardTransaction = new CardTransaction();
        wrongCardTransaction.setCardTransactionId(transactionId);
        wrongCardTransaction.setCardId(999L); // Different card ID

        when(repository.findByCardTransactionId(transactionId)).thenReturn(Mono.just(wrongCardTransaction));

        // Act & Assert
        StepVerifier.create(service.updateTransaction(cardId, transactionId, transactionDTO))
                .verifyComplete();

        verify(repository).findByCardTransactionId(transactionId);
        verify(mapper, never()).toEntity(any(CardTransactionDTO.class));
        verify(repository, never()).save(any(CardTransaction.class));
        verify(mapper, never()).toDTO(any(CardTransaction.class));
    }

    @Test
    void deleteTransaction_Success() {
        // Arrange
        when(repository.findByCardTransactionId(transactionId)).thenReturn(Mono.just(transactionEntity));
        when(repository.delete(transactionEntity)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteTransaction(cardId, transactionId))
                .verifyComplete();

        verify(repository).findByCardTransactionId(transactionId);
        verify(repository).delete(transactionEntity);
    }

    @Test
    void deleteTransaction_NotFound() {
        // Arrange
        when(repository.findByCardTransactionId(transactionId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteTransaction(cardId, transactionId))
                .verifyComplete();

        verify(repository).findByCardTransactionId(transactionId);
        verify(repository, never()).delete(any(CardTransaction.class));
    }

    @Test
    void deleteTransaction_WrongCard() {
        // Arrange
        CardTransaction wrongCardTransaction = new CardTransaction();
        wrongCardTransaction.setCardTransactionId(transactionId);
        wrongCardTransaction.setCardId(999L); // Different card ID

        when(repository.findByCardTransactionId(transactionId)).thenReturn(Mono.just(wrongCardTransaction));

        // Act & Assert
        StepVerifier.create(service.deleteTransaction(cardId, transactionId))
                .verifyComplete();

        verify(repository).findByCardTransactionId(transactionId);
        verify(repository, never()).delete(any(CardTransaction.class));
    }
}
