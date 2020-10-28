package com.neluplatonov.eurder.api.controllers;

import com.neluplatonov.eurder.api.dtos.customerDtos.NewCustomerDto;
import com.neluplatonov.eurder.api.mappers.CustomerMapper;
import com.neluplatonov.eurder.domain.Customer;
import com.neluplatonov.eurder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public String createCustomer(@RequestBody NewCustomerDto newCustomerDto){
        Customer newCustomer = CustomerMapper.convertNewCustomerDtoToCustomer(newCustomerDto);
        return customerService.registerNewCustomer(newCustomer);
    }

}
