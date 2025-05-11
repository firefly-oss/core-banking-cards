package com.catalis.core.banking.cards.core.services.customer.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.customer.v1.CardCustomerMapper;
import com.catalis.core.banking.cards.interfaces.dtos.customer.v1.CardCustomerDTO;
import com.catalis.core.banking.cards.models.entities.customer.v1.CardCustomer;
import com.catalis.core.banking.cards.models.repositories.customer.v1.CardCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardCustomerServiceImpl implements CardCustomerService {

    @Autowired
    private CardCustomerRepository repository;

    @Autowired
    private CardCustomerMapper mapper;

    @Override
    public Mono<PaginationResponse<CardCustomerDTO>> listCustomers(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                repository::findAllBy,
                repository::count
        );
    }

    @Override
    public Mono<CardCustomerDTO> createCustomer(CardCustomerDTO customerDTO) {
        CardCustomer entity = mapper.toEntity(customerDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardCustomerDTO> getCustomer(Long customerId) {
        return repository.findByCustomerId(customerId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardCustomerDTO> updateCustomer(Long customerId, CardCustomerDTO customerDTO) {
        return repository.findByCustomerId(customerId)
                .flatMap(existingCustomer -> {
                    CardCustomer updatedCustomer = mapper.toEntity(customerDTO);
                    updatedCustomer.setCustomerId(existingCustomer.getCustomerId());
                    return repository.save(updatedCustomer);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteCustomer(Long customerId) {
        return repository.findByCustomerId(customerId)
                .flatMap(repository::delete);
    }
}
