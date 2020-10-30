package com.neluplatonov.eurder.api.controllers;

import com.neluplatonov.eurder.api.dtos.customerDtos.NewCustomerDto;
import com.neluplatonov.eurder.api.mappers.CustomerMapper;
import com.neluplatonov.eurder.domain.Customer;
import com.neluplatonov.eurder.domain.Report;
import com.neluplatonov.eurder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Customer createCustomer(@RequestBody NewCustomerDto newCustomerDto){
        Customer newCustomer = CustomerMapper.convertNewCustomerDtoToCustomer(newCustomerDto);

        customerService.registerNewCustomer(newCustomer);

        return newCustomer;
    }

    @GetMapping
    public List<Customer> getAllCustomers(@RequestHeader String userId){
        return customerService.getAllCustomers(userId);
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable String customerId, @RequestHeader String userId){
        return customerService.getCustomerById(customerId, userId);
    }
}
