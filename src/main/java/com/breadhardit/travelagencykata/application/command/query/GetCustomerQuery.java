package com.breadhardit.travelagencykata.application.command.query;

import com.breadhardit.travelagencykata.domain.Customer;
import com.breadhardit.travelagencykata.application.port.CustomersRepository;
import lombok.Builder;
import lombok.Value;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Value
@Builder
public class GetCustomerQuery {
    String passport;
    String id;
}
