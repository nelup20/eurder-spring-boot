package com.neluplatonov.eurder.service;

import com.neluplatonov.eurder.domain.Customer;
import com.neluplatonov.eurder.exception.AdminPrivilegeException;
import com.neluplatonov.eurder.repository.AdminDatabase;
import com.neluplatonov.eurder.repository.CustomerDatabase;
import com.neluplatonov.eurder.validator.CustomerValidator;
import com.neluplatonov.eurder.validator.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerService {

    private CustomerDatabase customerDatabase;
    private AdminDatabase adminDatabase;

    @Autowired
    public CustomerService(CustomerDatabase customerDatabase, AdminDatabase adminDatabase) {
        this.customerDatabase = customerDatabase;
        this.adminDatabase = adminDatabase;
    }

    public void registerNewCustomer(Customer newCustomer){
        CustomerValidator.validateEmail(newCustomer.getEmailAddress());
        CustomerValidator.validateArgumentsAreNotEmpty(List.of(newCustomer.getFirstName(), newCustomer.getLastName(), newCustomer.getAddress(), newCustomer.getPhoneNumber()));

        customerDatabase.registerNewCustomer(newCustomer);
    }

    public List<Customer> getAllCustomers(String userId){
        IdValidator.validateSingleUUID(userId);
        if(!adminDatabase.isUserAnAdmin(userId)) throw new AdminPrivilegeException("The provided user ID is not for an admin! Only admins may view all the customers.");

        return customerDatabase.getAllCustomers();
    }

    public Customer getCustomerById(String customerId, String userId){
        IdValidator.validateListOfUUIDs(List.of(customerId, userId));
        if(!adminDatabase.isUserAnAdmin(userId)) throw new AdminPrivilegeException("The provided user ID is not for an admin! Only admins can view the details of a single customer.");

        return customerDatabase.getCustomerById(customerId);
    }
}
