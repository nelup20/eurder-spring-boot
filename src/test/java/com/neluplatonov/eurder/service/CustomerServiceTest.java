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
    void givenNewCustomerService_whenRegistering1NewCorrectCustomer_thenNewCustomerIsInDatabase(){
        //given
        CustomerDatabase customerDatabase = new CustomerDatabase();
        CustomerService customerService = new CustomerService(customerDatabase);

        //when
        Customer newCustomer1 = new Customer("John", "Doe", "john.doe@gmail.com", "abc. street", "04951251");
        customerService.registerNewCustomer(newCustomer1);

        //then
        assertEquals(newCustomer1, customerDatabase.getCustomerById(newCustomer1.getId()));
    }


    @Test
    void givenNewCustomerService_whenRegisteringNewCustomerWithEmptyEmail_thenThrowsIllegalArgumentException(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase());

        //when
        Customer newCustomer = new Customer("John", "Doe", "", "abc. street", "04951251");

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.registerNewCustomer(newCustomer);
        });
    }

    @Test
    void givenNewCustomerService_whenRegisteringNewCustomerWithEmptyFirstName_thenThrowsIllegalArgumentException(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase());

        //when
        Customer newCustomer = new Customer("", "Doe", "josh@gmail.com", "abc. street", "04951251");

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.registerNewCustomer(newCustomer);
        });
    }

    @Test
    void givenNewCustomerService_whenRegisteringNewCustomerWithEmptyLastName_thenThrowsIllegalArgumentException(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase());

        //when
        Customer newCustomer = new Customer("Bob", "", "josh@gmail.com", "abc. street", "04951251");

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.registerNewCustomer(newCustomer);
        });
    }

    @Test
    void givenNewCustomerService_whenRegisteringNewCustomerWithEmptyAddress_thenThrowsIllegalArgumentException(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase());

        //when
        Customer newCustomer = new Customer("Asd", "Doe", "josh@gmail.com", "", "04951251");

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.registerNewCustomer(newCustomer);
        });
    }

    @Test
    void givenNewCustomerService_whenRegisteringNewCustomerWithEmptyPhoneNumber_thenThrowsIllegalArgumentException(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase());

        //when
        Customer newCustomer = new Customer("asdasd", "Doe", "josh@gmail.com", "abc. street", "");

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.registerNewCustomer(newCustomer);
        });
    }
}