package com.breadhardit.travelagencykata.infrastructure.rest;

import com.breadhardit.travelagencykata.application.command.command.CreateCustomerCommand;
import com.breadhardit.travelagencykata.application.command.command.CreateCustomerHandler;
import com.breadhardit.travelagencykata.application.command.query.GetCustomerHandler;
import com.breadhardit.travelagencykata.application.command.query.GetCustomerQuery;
import com.breadhardit.travelagencykata.application.port.CustomersRepository;
import com.breadhardit.travelagencykata.domain.Customer;
import com.breadhardit.travelagencykata.infrastructure.rest.dto.GetCustomerDTO;
import com.breadhardit.travelagencykata.infrastructure.rest.dto.PutCustomerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CustomersController {

    private final GetCustomerHandler getCustomerHandler;
    private final CreateCustomerHandler createCustomerHandler;

    @PutMapping("/customers")
    @Transactional
    public ResponseEntity putCustomer(@RequestBody PutCustomerDTO customer) {
        log.info("POST customer {}", customer);
        CreateCustomerCommand command = CreateCustomerCommand.builder()
                .name(customer.getName())
                .surnames(customer.getSurnames())
                .birthDate(customer.getBirthDate())
                .passportNumber(customer.getPassportNumber())
                .build();

        String id = createCustomerHandler.handle(command);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{customer-id}")
                        .buildAndExpand(id)
                        .toUri()).build();
    }

    @GetMapping("/customers/{customer-id}")
    public ResponseEntity getCustomer(@PathVariable String customerId) {
        log.info("Getting the customer {}", customerId);

        GetCustomerQuery query = GetCustomerQuery.builder()
                .id(customerId)
                .build();

        Optional<Customer> customer = getCustomerHandler.handle(query);

        return customer.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(GetCustomerDTO.builder()
                .name(customer.get().getName())
                .surnames(customer.get().getSurnames())
                .birthDate(customer.get().getBirthDate())
                .passportNumber(customer.get().getPassportNumber())
                .build());
    }
    @GetMapping("/customers")
    public ResponseEntity getCustomers(@RequestParam(name = "passport-number") String passportNumber) {
        log.info("Getting the customer with the passport {}",passportNumber);

        GetCustomerQuery query = GetCustomerQuery.builder()
                .passport(passportNumber)
                .build();

        Optional<Customer> customer = getCustomerHandler.handle(query);

        return customer.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(
                        List.of(GetCustomerDTO.builder()
                                .name(customer.get().getName())
                                .surnames(customer.get().getSurnames())
                                .birthDate(customer.get().getBirthDate())
                                .passportNumber(customer.get().getPassportNumber())
                                .build())
                );
    }

}
