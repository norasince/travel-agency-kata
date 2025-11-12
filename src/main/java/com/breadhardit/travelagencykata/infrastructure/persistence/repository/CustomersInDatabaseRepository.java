package com.breadhardit.travelagencykata.infrastructure.persistence.repository;

import com.breadhardit.travelagencykata.application.port.CustomersRepository;
import com.breadhardit.travelagencykata.domain.Customer;
import com.breadhardit.travelagencykata.infrastructure.persistence.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Repository
@Scope("singleton")
@RequiredArgsConstructor
public class CustomersInDatabaseRepository implements CustomersRepository {
    private final CustomersJPARepository customersJPARepository;

    @Override
    public void saveCustomer(Customer customer) {

        customersJPARepository.save(createEntityByCustomer(customer));
    }

    private CustomerEntity createEntityByCustomer(Customer customer){

        CustomerEntity.CustomerEntityBuilder builder = CustomerEntity.builder();
        builder.name(customer.getName());
        builder.id(customer.getId());
        builder.passportNumber(customer.getPassportNumber());
        builder.birthDate(customer.getBirthDate());
        builder.active(customer.getActive());
        builder.surnames(customer.getSurnames());
        builder.enrollmentDate(customer.getEnrollmentDate());

        return builder.build();

    }

    private Customer createCustomerByEntity(CustomerEntity customerEntity){

        Customer.CustomerBuilder builder = Customer.builder();
        builder.name(customerEntity.getName());
        builder.id(customerEntity.getId());
        builder.passportNumber(customerEntity.getPassportNumber());
        builder.birthDate(customerEntity.getBirthDate());
        builder.active(customerEntity.getActive());
        builder.surnames(customerEntity.getSurnames());
        builder.enrollmentDate(customerEntity.getEnrollmentDate());

        return builder.build();

    }

    @Override
    public Optional<Customer> getCustomerById(String id) {
        Optional<Customer> opt = Optional.of(createCustomerByEntity(customerEntity);
        CustomerEntity customerEntity = customersJPARepository.findById(id);
        return customerEntity == null ? Optional.empty() : Optional.of(createCustomerByEntity(customerEntity));
    }

    @Override
    public Optional<Customer> getCustomerByPassport(String id) {


    }
}
