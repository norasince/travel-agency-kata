package com.breadhardit.travelagencykata.application.command.command;

import com.breadhardit.travelagencykata.application.port.CustomersRepository;
import com.breadhardit.travelagencykata.domain.Customer;
import lombok.SneakyThrows;

public class CreateCustomerHandler {

    private CustomersRepository customersRepository;
    @SneakyThrows
    public String handle(CreateCustomerCommand command) {
        Customer customer = Customer.builder()
                .id(command.getId())
                .name(command.getName())
                .surnames(command.getSurnames())
                .birthDate(command.getBirthDate())
                .passportNumber(command.getPassportNumber())
                .build();

        customersRepository.saveCustomer(customer);
        return customer.getId();
    }
}
