package com.neluplatonov.eurder.repository;

import com.neluplatonov.eurder.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerDatabase {
    private Map<String, Customer> customers = new HashMap<>();

    public String registerNewCustomer(Customer newCustomer){
        if(!isEmailUnique(newCustomer.getEmailAddress())) throw new IllegalArgumentException("This email is already registered.");

        customers.put(newCustomer.getId(), newCustomer);
        return "Welcome " + newCustomer.getFirstName() + " " + newCustomer.getLastName() + " with email " + newCustomer.getEmailAddress() + ", you are now a Eurder customer!";
    }

    public boolean customerExists(Customer customerToCheck){
        return customers.get(customerToCheck.getId()) != null;
    }

    public boolean isEmailUnique(String emailToCheck){
        return customers.values().stream().noneMatch(customer -> customer.getEmailAddress().equals(emailToCheck));
    }
}
