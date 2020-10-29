package com.neluplatonov.eurder.repository;

import com.neluplatonov.eurder.domain.Customer;
import com.neluplatonov.eurder.exception.NoCustomerFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CustomerDatabase {
    private Map<String, Customer> customers = new HashMap<>();

    public CustomerDatabase() {
        // Populating/Seeding initial database
        Customer initialCustomer1 = new Customer("John", "Doe", "johndoe.initialCustomer@gmail.com", "New street 23", "04953122");
        initialCustomer1.setId("c6093628-b11a-4ece-b2f0-509fc0f3c132");
        customers.put(initialCustomer1.getId(), initialCustomer1);

        Customer initialCustomer2 = new Customer("Tom", "Vu", "tomvu.initialCustomer@gmail.com", "New street 23", "04953122");
        initialCustomer2.setId("a1183628-b11a-4ece-b2f0-509fc0f3c132");
        customers.put(initialCustomer2.getId(), initialCustomer2);
    }

    public void registerNewCustomer(Customer newCustomer){
        if(!isEmailUnique(newCustomer.getEmailAddress())) throw new IllegalArgumentException("This email is already registered.");

        customers.put(newCustomer.getId(), newCustomer);
    }

    public boolean customerExists(String customerId){
        return customers.get(customerId) != null;
    }

    public boolean isEmailUnique(String emailToCheck){
        return customers.values().stream().noneMatch(customer -> customer.getEmailAddress().equals(emailToCheck));
    }

    public Customer getCustomerById(String customerId){
        if(!customerExists(customerId)) throw new NoCustomerFoundException("The customer with the provided ID does not exist!");

        return customers.get(customerId);
    }

    public List<Customer> getAllCustomers(){
        return new ArrayList<>(customers.values());
    }
}
