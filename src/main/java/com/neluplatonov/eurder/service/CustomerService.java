package com.neluplatonov.eurder.service;

import com.neluplatonov.eurder.domain.Customer;
import com.neluplatonov.eurder.repository.CustomerDatabase;
import com.neluplatonov.eurder.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerService {

    private CustomerDatabase customerDatabase;

    @Autowired
    public CustomerService(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public void registerNewCustomer(Customer newCustomer){
        CustomerValidator.validateEmail(newCustomer.getEmailAddress());
        CustomerValidator.validateArgumentsAreNotEmpty(List.of(newCustomer.getFirstName(), newCustomer.getLastName(), newCustomer.getAddress(), newCustomer.getPhoneNumber()));

        customerDatabase.registerNewCustomer(newCustomer);
    }
}
