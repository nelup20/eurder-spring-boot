package com.neluplatonov.eurder.api.controllers;

import com.neluplatonov.eurder.api.dtos.itemgroupdtos.NewItemGroupDto;
import com.neluplatonov.eurder.api.dtos.orderdtos.NewlyCreatedOrderDto;
import com.neluplatonov.eurder.api.mappers.ItemGroupMapper;
import com.neluplatonov.eurder.api.mappers.OrderMapper;
import com.neluplatonov.eurder.domain.ItemGroup;
import com.neluplatonov.eurder.domain.Report;
import com.neluplatonov.eurder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Story 3 - Order items
    @PostMapping
    public NewlyCreatedOrderDto createOrder(@RequestHeader String customerId, @RequestBody List<NewItemGroupDto> newOrderItemsDto){
        orderService.checkIfAllItemIdsExistInItemDatabase(newOrderItemsDto);

        List<ItemGroup> orderItems = ItemGroupMapper.convertListOfItemGroupDtosToListOfItemGroups(newOrderItemsDto);

        return OrderMapper.convertOrderToNewlyCreatedOrderDto(orderService.createOrder(customerId, orderItems));
    }


    // Story 5 - View report of orders
    @GetMapping("/my-orders")
    public Report getOrdersReport(@RequestHeader String customerId){
        return orderService.getOrdersReport(customerId);
    }
}
