package com.neluplatonov.eurder.api.controllers;

import com.neluplatonov.eurder.api.dtos.customerDtos.NewCustomerDto;
import com.neluplatonov.eurder.api.mappers.CustomerMapper;
import com.neluplatonov.eurder.domain.Customer;
import com.neluplatonov.eurder.domain.Report;
import com.neluplatonov.eurder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Story 1 - Create a customer account
    @PostMapping
    public Customer createCustomer(@RequestBody NewCustomerDto newCustomerDto){
        Customer newCustomer = CustomerMapper.convertNewCustomerDtoToCustomer(newCustomerDto);

        customerService.registerNewCustomer(newCustomer);

        return newCustomer;
    }

    // Story 7 - View all customers
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAllCustomers(@RequestHeader String userId){
        return customerService.getAllCustomers(userId);
    }

    // Story 8 - View a single customer
    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable String customerId, @RequestHeader String userId){
        return customerService.getCustomerById(customerId, userId);
    }
}
