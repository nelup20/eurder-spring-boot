package com.neluplatonov.eurder.api.mappers;

import com.neluplatonov.eurder.api.dtos.customerDtos.NewCustomerDto;
import com.neluplatonov.eurder.domain.Customer;

public class CustomerMapper {

    // Ok nvm not gonna use the builder pattern since for now this is still ok and a builder pattern would imo not clear up things much.
    public static Customer convertNewCustomerDtoToCustomer(NewCustomerDto newCustomerDto){
        return new Customer(newCustomerDto.getFirstName(), newCustomerDto.getLastName(), newCustomerDto.getEmailAddress(), newCustomerDto.getAddress(), newCustomerDto.getPhoneNumber());
    }
}
