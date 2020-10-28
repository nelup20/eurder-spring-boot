package com.neluplatonov.eurder.service;

import com.neluplatonov.eurder.domain.Customer;
import com.neluplatonov.eurder.repository.CustomerDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @Test
    void givenNewCustomerService_whenRegisteringNewCustomerWithInvalidEmail_thenThrowsIllegalArgumentException(){
      //given
      CustomerService customerService = new CustomerService(new CustomerDatabase());

      //when
      Customer newCustomer = new Customer("John", "Doe", "joooe", "abc. street", "04951251");

      //then
      assertThrows(IllegalArgumentException.class, () -> {
          customerService.registerNewCustomer(newCustomer);
      });
    }

    @Test
    void givenNewCustomerService_whenRegistering2DifferentCustomersWithTheSameEmail_thenThrowsIllegalArgumentException(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase());

        //when
        Customer newCustomer1 = new Customer("John", "Doe", "john.doe@gmail.com", "abc. street", "04951251");
        Customer newCustomer2 = new Customer("Jane", "Doe", "john.doe@gmail.com", "abc. street", "04951251");
        customerService.registerNewCustomer(newCustomer1);

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.registerNewCustomer(newCustomer2);
        });
    }

    @Test
    void givenNewCustomerService_whenRegistering1NewCorrectCustomer_thenReturnsCorrectWelcomeMessage(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase());

        //when
        Customer newCustomer1 = new Customer("John", "Doe", "john.doe@gmail.com", "abc. street", "04951251");
        String welcomeMessage = customerService.registerNewCustomer(newCustomer1);

        //then
        assertEquals("Welcome John Doe with email john.doe@gmail.com, you are now a Eurder customer!", welcomeMessage);
    }

}