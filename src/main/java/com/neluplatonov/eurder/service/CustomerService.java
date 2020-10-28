package com.neluplatonov.eurder.service;

import com.neluplatonov.eurder.domain.Customer;
import com.neluplatonov.eurder.repository.CustomerDatabase;
import com.neluplatonov.eurder.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {

    private CustomerDatabase customerDatabase;

    @Autowired
    public CustomerService(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public String registerNewCustomer(Customer newCustomer){
        CustomerValidator.validateEmail(newCustomer.getEmailAddress());

        return customerDatabase.registerNewCustomer(newCustomer);
    }
}
