package com.breadhardit.travelagencykata.application.command.query;

import com.breadhardit.travelagencykata.application.port.CustomersRepository;
import com.breadhardit.travelagencykata.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetCustomerHandler {

    private final CustomersRepository customersRepository;

    public Optional<Customer> handle(GetCustomerQuery query) {

        if (StringUtils.hasText(query.getPassport()) && !StringUtils.hasText(query.getId())) {
            return customersRepository.getCustomerByPassport(query.getPassport());
        }

        if (StringUtils.hasText(query.getId()) && !StringUtils.hasText(query.getPassport())) {
            return customersRepository.getCustomerById(query.getId());
        }

        return Optional.empty();
    }
}
