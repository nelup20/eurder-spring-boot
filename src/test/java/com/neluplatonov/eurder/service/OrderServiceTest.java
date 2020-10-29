package com.neluplatonov.eurder.service;

import com.neluplatonov.eurder.api.dtos.itemgroupdtos.ItemGroupDto;
import com.neluplatonov.eurder.api.dtos.orderdtos.ReportOrderDto;
import com.neluplatonov.eurder.domain.ItemGroup;
import com.neluplatonov.eurder.domain.Order;
import com.neluplatonov.eurder.domain.Report;
import com.neluplatonov.eurder.exception.NoCustomerFoundException;
import com.neluplatonov.eurder.exception.NoOrdersFoundException;
import com.neluplatonov.eurder.repository.CustomerDatabase;
import com.neluplatonov.eurder.repository.ItemDatabase;
import com.neluplatonov.eurder.repository.OrderDatabase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void givenNewOrderService_whenCreatingNewOrderWithInvalidCustomerId_thenThrowsIllegalArgumentException() {
        //given
        OrderDatabase orderDatabase = new OrderDatabase();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        ItemDatabase itemDatabase = new ItemDatabase();
        OrderService orderService = new OrderService(orderDatabase, customerDatabase, itemDatabase);

        //when
        List<ItemGroup> orderItems = List.of(new ItemGroup("44492ce0-dfca-49f5-b519-0bf2839f2d64", 2), new ItemGroup("bc23cbd0-fc7a-404d-a473-39711a0f7c7c", 3));
        String customerId = "123456";

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.createOrder(customerId, orderItems);
        });
    }

    @Test
    void givenNewOrderService_whenCreatingNewOrderWithNonCustomerId_thenThrowsIllegalArgumentException() {
        //given
        OrderDatabase orderDatabase = new OrderDatabase();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        ItemDatabase itemDatabase = new ItemDatabase();
        OrderService orderService = new OrderService(orderDatabase, customerDatabase, itemDatabase);

        //when
        List<ItemGroup> orderItems = List.of(new ItemGroup("44492ce0-dfca-49f5-b519-0bf2839f2d64", 2), new ItemGroup("bc23cbd0-fc7a-404d-a473-39711a0f7c7c", 3));
        String customerId = "44492ce0-dfca-49f5-b519-0bf2839f2d64";

        //then
        assertThrows(NoCustomerFoundException.class, () -> {
            orderService.createOrder(customerId, orderItems);
        });
    }

    @Test
    void givenNewOrderService_whenCheckingIfAllItemIdsExistInItemDatabaseWith1ItemThatHasInvalidId_thenThrowsIllegalArgumentException() {
        //given
        OrderDatabase orderDatabase = new OrderDatabase();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        ItemDatabase itemDatabase = new ItemDatabase();
        OrderService orderService = new OrderService(orderDatabase, customerDatabase, itemDatabase);

        //when
        List<ItemGroupDto> orderItems = List.of(new ItemGroupDto("12345", 2), new ItemGroupDto("bc23cbd0-fc7a-404d-a473-39711a0f7c7c", 3));

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.checkIfAllItemIdsExistInItemDatabase(orderItems);
        });
    }

    @Test
    void givenNewOrderService_whenCheckingIfAllItemIdsExistInItemDatabaseWith1ItemThatHasNonItemId_thenThrowsIllegalArgumentException() {
        //given
        OrderDatabase orderDatabase = new OrderDatabase();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        ItemDatabase itemDatabase = new ItemDatabase();
        OrderService orderService = new OrderService(orderDatabase, customerDatabase, itemDatabase);

        //when
        List<ItemGroupDto> orderItems = List.of(new ItemGroupDto("c6093628-b11a-4ece-b2f0-509fc0f3c132", 2), new ItemGroupDto("bc23cbd0-fc7a-404d-a473-39711a0f7c7c", 3));

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.checkIfAllItemIdsExistInItemDatabase(orderItems);
        });
    }

    @Test
    void givenNewOrderService_whenCreatingNewCorrectOrderWith2Items_thenNoExceptionIsThrown() {
        //given
        OrderDatabase orderDatabase = new OrderDatabase();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        ItemDatabase itemDatabase = new ItemDatabase();
        OrderService orderService = new OrderService(orderDatabase, customerDatabase, itemDatabase);

        //when
        List<ItemGroup> orderItems = List.of(new ItemGroup("44492ce0-dfca-49f5-b519-0bf2839f2d64", 2), new ItemGroup("bc23cbd0-fc7a-404d-a473-39711a0f7c7c", 3));
        String customerId = "c6093628-b11a-4ece-b2f0-509fc0f3c132";

        //then
        assertDoesNotThrow(() -> {
            orderService.createOrder(customerId, orderItems);
        });
    }

    @Test
    void givenNewOrderService_whenCreatingNewCorrectOrderWith2Items_thenOrderDatabaseSizeIs1() {
        //given
        OrderDatabase orderDatabase = new OrderDatabase();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        ItemDatabase itemDatabase = new ItemDatabase();
        OrderService orderService = new OrderService(orderDatabase, customerDatabase, itemDatabase);

        //when
        List<ItemGroup> orderItems = List.of(new ItemGroup("44492ce0-dfca-49f5-b519-0bf2839f2d64", 2), new ItemGroup("bc23cbd0-fc7a-404d-a473-39711a0f7c7c", 3));
        String customerId = "c6093628-b11a-4ece-b2f0-509fc0f3c132";
        orderService.createOrder(customerId, orderItems);

        //then
        assertEquals(1, orderDatabase.getAllOrders().size());
    }

    @Test
    void givenNewOrderService_whenCreatingNewCorrectOrderWith2Items_thenNewOrderTotalCostIs13() {
        //given
        OrderDatabase orderDatabase = new OrderDatabase();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        ItemDatabase itemDatabase = new ItemDatabase();
        OrderService orderService = new OrderService(orderDatabase, customerDatabase, itemDatabase);

        //when
        List<ItemGroup> orderItems = List.of(new ItemGroup("44492ce0-dfca-49f5-b519-0bf2839f2d64", 2), new ItemGroup("bc23cbd0-fc7a-404d-a473-39711a0f7c7c", 3));
        String customerId = "c6093628-b11a-4ece-b2f0-509fc0f3c132";
        Order newOrder = orderService.createOrder(customerId, orderItems);

        //then
        assertEquals(13, newOrder.getTotalOrderCostInEuros());
    }

    @Test
    void givenNewOrderService_whenCreatingNewCorrectOrderWith2Items_thenShippingDateOfFirstItemIsNextDay() {
        //given
        OrderDatabase orderDatabase = new OrderDatabase();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        ItemDatabase itemDatabase = new ItemDatabase();
        OrderService orderService = new OrderService(orderDatabase, customerDatabase, itemDatabase);

        //when
        List<ItemGroup> orderItems = List.of(new ItemGroup("44492ce0-dfca-49f5-b519-0bf2839f2d64", 2), new ItemGroup("bc23cbd0-fc7a-404d-a473-39711a0f7c7c", 3));
        String customerId = "c6093628-b11a-4ece-b2f0-509fc0f3c132";
        Order newOrder = orderService.createOrder(customerId, orderItems);

        //then
        assertEquals(LocalDate.now().plusDays(1), newOrder.getItems().get(0).getShippingDate());
    }

    @Test
    void givenNewOrderService_whenCreatingNewCorrectOrderWith3Items_thenShippingDateOfThirdItemIsNextWeek() {
        //given
        OrderDatabase orderDatabase = new OrderDatabase();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        ItemDatabase itemDatabase = new ItemDatabase();
        OrderService orderService = new OrderService(orderDatabase, customerDatabase, itemDatabase);

        //when
        List<ItemGroup> orderItems = List.of(new ItemGroup("44492ce0-dfca-49f5-b519-0bf2839f2d64", 2), new ItemGroup("bc23cbd0-fc7a-404d-a473-39711a0f7c7c", 3), new ItemGroup("c0b6efc9-ed65-448d-a06e-21a1ed4b48c8", 11));
        String customerId = "c6093628-b11a-4ece-b2f0-509fc0f3c132";
        Order newOrder = orderService.createOrder(customerId, orderItems);

        //then
        assertEquals(LocalDate.now().plusWeeks(1), newOrder.getItems().get(2).getShippingDate());
    }

    @Test
    void givenNewOrderService_whenGettingOrdersReportAndProvidingInvalidCustomerId_thenThrowsIllegalArgumentException() {
        //given
        OrderDatabase orderDatabase = new OrderDatabase();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        ItemDatabase itemDatabase = new ItemDatabase();
        OrderService orderService = new OrderService(orderDatabase, customerDatabase, itemDatabase);

        //when
        String customerId = "asd1234";

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.getOrdersReport(customerId);
        });
    }

    @Test
    void givenNewOrderService_whenGettingOrdersReportAndNonCustomerId_thenThrowsIllegalArgumentException() {
        //given
        OrderDatabase orderDatabase = new OrderDatabase();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        ItemDatabase itemDatabase = new ItemDatabase();
        OrderService orderService = new OrderService(orderDatabase, customerDatabase, itemDatabase);

        //when
        String customerId = "c0b6efc9-ed65-448d-a06e-21a1ed4b48c8";

        //then
        assertThrows(NoCustomerFoundException.class, () -> {
            orderService.getOrdersReport(customerId);
        });
    }

    @Test
    void givenNewOrderService_whenGettingOrdersReportForCustomerWithNoOrders_thenThrowsIllegalArgumentException() {
        //given
        OrderDatabase orderDatabase = new OrderDatabase();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        ItemDatabase itemDatabase = new ItemDatabase();
        OrderService orderService = new OrderService(orderDatabase, customerDatabase, itemDatabase);

        //when
        String customerId = "a1183628-b11a-4ece-b2f0-509fc0f3c132";

        //then
        assertThrows(NoOrdersFoundException.class, () -> {
            orderService.getOrdersReport(customerId);
        });
    }

    @Test
    void givenNewOrderService_whenGettingOrdersReport_thenReportTotalOrdersCostEquals563() {
        //given
        OrderDatabase orderDatabase = new OrderDatabase();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        ItemDatabase itemDatabase = new ItemDatabase();
        OrderService orderService = new OrderService(orderDatabase, customerDatabase, itemDatabase);

        //when
        List<ItemGroup> orderItems = List.of(new ItemGroup("44492ce0-dfca-49f5-b519-0bf2839f2d64", 2), new ItemGroup("bc23cbd0-fc7a-404d-a473-39711a0f7c7c", 3), new ItemGroup("c0b6efc9-ed65-448d-a06e-21a1ed4b48c8", 11));
        String customerId = "c6093628-b11a-4ece-b2f0-509fc0f3c132";
        Order newOrder = orderService.createOrder(customerId, orderItems);
        Report reportToCompareTo = orderService.getOrdersReport(customerId);

        //then
        assertEquals(563, reportToCompareTo.getTotalPriceOfAllOrdersInEuros());
    }

    @Test
    void givenNewOrderService_whenGettingOrdersReport_thenReportContains3Orders() {
        //given
        OrderDatabase orderDatabase = new OrderDatabase();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        ItemDatabase itemDatabase = new ItemDatabase();
        OrderService orderService = new OrderService(orderDatabase, customerDatabase, itemDatabase);

        //when
        List<ItemGroup> orderItems = List.of(new ItemGroup("44492ce0-dfca-49f5-b519-0bf2839f2d64", 2), new ItemGroup("bc23cbd0-fc7a-404d-a473-39711a0f7c7c", 3), new ItemGroup("c0b6efc9-ed65-448d-a06e-21a1ed4b48c8", 11));
        String customerId = "c6093628-b11a-4ece-b2f0-509fc0f3c132";
        orderService.createOrder(customerId, orderItems);
        orderService.createOrder(customerId, orderItems);
        orderService.createOrder(customerId, orderItems);
        Report reportToCompareTo = orderService.getOrdersReport(customerId);

        //then
        assertEquals(3, reportToCompareTo.getOrders().size());
    }
}