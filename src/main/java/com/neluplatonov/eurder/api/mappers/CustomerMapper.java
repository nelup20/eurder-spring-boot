package com.neluplatonov.eurder.api.mappers;

import com.neluplatonov.eurder.api.dtos.customerDtos.NewCustomerDto;
import com.neluplatonov.eurder.domain.Customer;

public class CustomerMapper {

    // TODO: refactor using builder pattern
    public static Customer convertNewCustomerDtoToCustomer(NewCustomerDto newCustomerDto){
        return new Customer(newCustomerDto.getFirstName(), newCustomerDto.getLastName(), newCustomerDto.getEmailAddress(), newCustomerDto.getAddress(), newCustomerDto.getPhoneNumber());
    }
}
