package com.neluplatonov.eurder.service;

import com.neluplatonov.eurder.domain.Customer;
import com.neluplatonov.eurder.exception.AdminPrivilegeException;
import com.neluplatonov.eurder.repository.AdminDatabase;
import com.neluplatonov.eurder.repository.CustomerDatabase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @Test
    void givenNewCustomerService_whenRegisteringNewCustomerWithInvalidEmail_thenThrowsIllegalArgumentException(){
      //given
      CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

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
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

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
        CustomerService customerService = new CustomerService(customerDatabase, new AdminDatabase());

        //when
        Customer newCustomer1 = new Customer("John", "Doe", "john.doe@gmail.com", "abc. street", "04951251");
        customerService.registerNewCustomer(newCustomer1);

        //then
        assertEquals(newCustomer1, customerDatabase.getCustomerById(newCustomer1.getId()));
    }


    @Test
    void givenNewCustomerService_whenRegisteringNewCustomerWithEmptyEmail_thenThrowsIllegalArgumentException(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

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
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

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
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

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
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

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
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

        //when
        Customer newCustomer = new Customer("asdasd", "Doe", "josh@gmail.com", "abc. street", "");

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.registerNewCustomer(newCustomer);
        });
    }

    @Test
    void givenNewCustomerService_whenGettingAllCustomersAndProvidingInvalidUserId_thenThrowsIllegalArgumentException(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

        //when
        String adminId = "12346";

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.getAllCustomers(adminId);
        });
    }

    @Test
    void givenNewCustomerService_whenGettingAllCustomersAndProvidingNonAdminId_thenThrowsAdminPrivilegeException(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

        //when
        String adminId = "44492ce0-dfca-49f5-b519-0bf2839f2d64";

        //then
        assertThrows(AdminPrivilegeException.class, () -> {
            customerService.getAllCustomers(adminId);
        });
    }

    @Test
    void givenNewCustomerService_whenGettingAllCustomers_thenListContainsInitialCustomer(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

        //when
        String adminId = "de6def71-53ca-4e5e-85ef-9ed3ab598391";
        Customer initialCustomer = new Customer("John", "Doe", "johndoe.initialCustomer@gmail.com", "New street 23", "04953122");
        initialCustomer.setId("c6093628-b11a-4ece-b2f0-509fc0f3c132");

        List<Customer> resultList = customerService.getAllCustomers(adminId);

        //then
        assertTrue(resultList.contains(initialCustomer));
    }

    @Test
    void givenNewCustomerService_whenGettingAllCustomers_thenListDoesNotContainCustomerThatWasNotAdded(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

        //when
        String adminId = "de6def71-53ca-4e5e-85ef-9ed3ab598391";
        Customer initialCustomer = new Customer("John", "Doe", "johndoe.initialCustomer@gmail.com", "New street 23", "04953122");
        initialCustomer.setId("c6093628-b11a-4ece-b2f0-509fc0f3c132");

        Customer nonEurderCustomer = new Customer("Bob", "Doe", "bobby@gmail.com", "New street 23", "04953122");
        nonEurderCustomer.setId("d7093628-b11a-4ece-b2f0-509fc0f3c132");

        List<Customer> resultList = customerService.getAllCustomers(adminId);

        //then
        assertFalse(resultList.contains(nonEurderCustomer));
    }

    @Test
    void givenNewCustomerService_whenGettingCustomerByIdAndProvidingInvalidCustomerId_thenThrowsIllegalArgumentException(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

        //when
        String adminId = "de6def71-53ca-4e5e-85ef-9ed3ab598391";
        Customer initialCustomer = new Customer("John", "Doe", "johndoe.initialCustomer@gmail.com", "New street 23", "04953122");
        initialCustomer.setId("c6093628-b11a-4ece-b2f0-509fc0f3c132");

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.getCustomerById("12345", adminId);
        });
    }

    @Test
    void givenNewCustomerService_whenGettingCustomerByIdAndProvidingInvalidUserId_thenThrowsIllegalArgumentException(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

        //when
        String adminId = "de6def71-53ca-4e5e-85ef-9ed3ab598391";
        Customer initialCustomer = new Customer("John", "Doe", "johndoe.initialCustomer@gmail.com", "New street 23", "04953122");
        initialCustomer.setId("c6093628-b11a-4ece-b2f0-509fc0f3c132");

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.getCustomerById(initialCustomer.getId(), "123456");
        });
    }

    @Test
    void givenNewCustomerService_whenGettingCustomerByIdAndProvidingNonCustomerId_thenThrowsIllegalArgumentException(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

        //when
        String adminId = "de6def71-53ca-4e5e-85ef-9ed3ab598391";
        Customer initialCustomer = new Customer("John", "Doe", "johndoe.initialCustomer@gmail.com", "New street 23", "04953122");
        initialCustomer.setId("c6093628-b11a-4ece-b2f0-509fc0f3c132");
        String itemId = "44492ce0-dfca-49f5-b519-0bf2839f2d64";

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.getCustomerById(itemId, adminId);
        });
    }

    @Test
    void givenNewCustomerService_whenGettingCustomerByIdAndProvidingNonAdminId_thenThrowsAdminPrivilegeException(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

        //when
        String adminId = "de6def71-53ca-4e5e-85ef-9ed3ab598391";
        Customer initialCustomer = new Customer("John", "Doe", "johndoe.initialCustomer@gmail.com", "New street 23", "04953122");
        initialCustomer.setId("c6093628-b11a-4ece-b2f0-509fc0f3c132");
        String itemId = "44492ce0-dfca-49f5-b519-0bf2839f2d64";

        //then
        assertThrows(AdminPrivilegeException.class, () -> {
            customerService.getCustomerById(initialCustomer.getId(), itemId);
        });
    }

    @Test
    void givenNewCustomerService_whenGettingCustomerById_thenReturnedCustomerEqualsInitialCustomer(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

        //when
        String adminId = "de6def71-53ca-4e5e-85ef-9ed3ab598391";
        Customer initialCustomer = new Customer("John", "Doe", "johndoe.initialCustomer@gmail.com", "New street 23", "04953122");
        initialCustomer.setId("c6093628-b11a-4ece-b2f0-509fc0f3c132");

        //then
        assertEquals(initialCustomer, customerService.getCustomerById(initialCustomer.getId(), adminId));
    }

    @Test
    void givenNewCustomerService_whenGettingCustomerById_thenReturnedCustomerDoesNotEqualNonEurderCustomer(){
        //given
        CustomerService customerService = new CustomerService(new CustomerDatabase(), new AdminDatabase());

        //when
        String adminId = "de6def71-53ca-4e5e-85ef-9ed3ab598391";
        Customer initialCustomer = new Customer("John", "Doe", "johndoe.initialCustomer@gmail.com", "New street 23", "04953122");
        initialCustomer.setId("c6093628-b11a-4ece-b2f0-509fc0f3c132");

        Customer nonEurderCustomer = new Customer("Bob", "Doe", "bobby@gmail.com", "New street 23", "04953122");
        nonEurderCustomer.setId("d7093628-b11a-4ece-b2f0-509fc0f3c132");

        //then
        assertNotEquals(nonEurderCustomer, customerService.getCustomerById(initialCustomer.getId(), adminId));
    }
}