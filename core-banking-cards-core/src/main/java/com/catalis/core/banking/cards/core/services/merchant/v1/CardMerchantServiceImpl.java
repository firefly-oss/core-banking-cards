package com.catalis.core.banking.cards.core.services.merchant.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.merchant.v1.CardMerchantMapper;
import com.catalis.core.banking.cards.interfaces.dtos.merchant.v1.CardMerchantDTO;
import com.catalis.core.banking.cards.models.entities.merchant.v1.CardMerchant;
import com.catalis.core.banking.cards.models.repositories.merchant.v1.CardMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardMerchantServiceImpl implements CardMerchantService {

    @Autowired
    private CardMerchantRepository repository;

    @Autowired
    private CardMerchantMapper mapper;

    @Override
    public Mono<PaginationResponse<CardMerchantDTO>> listMerchants(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                repository::findAllBy,
                repository::count
        );
    }

    @Override
    public Mono<CardMerchantDTO> createMerchant(CardMerchantDTO merchantDTO) {
        CardMerchant entity = mapper.toEntity(merchantDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardMerchantDTO> getMerchant(Long merchantId) {
        return repository.findByMerchantId(merchantId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardMerchantDTO> updateMerchant(Long merchantId, CardMerchantDTO merchantDTO) {
        return repository.findByMerchantId(merchantId)
                .flatMap(existingMerchant -> {
                    CardMerchant updatedMerchant = mapper.toEntity(merchantDTO);
                    updatedMerchant.setMerchantId(existingMerchant.getMerchantId());
                    return repository.save(updatedMerchant);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteMerchant(Long merchantId) {
        return repository.findByMerchantId(merchantId)
                .flatMap(repository::delete);
    }
}
