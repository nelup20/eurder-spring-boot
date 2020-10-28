package com.neluplatonov.eurder.api.controllers;

import com.neluplatonov.eurder.api.dtos.itemgroupdtos.ItemGroupDto;
import com.neluplatonov.eurder.api.dtos.orderDtos.CreatedOrderDto;
import com.neluplatonov.eurder.api.mappers.ItemGroupMapper;
import com.neluplatonov.eurder.domain.ItemGroup;
import com.neluplatonov.eurder.domain.Order;
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

    @PostMapping
    public Order createOrder(@RequestHeader String customerId, @RequestBody List<ItemGroupDto> orderItemsDto){
        orderService.checkIfAllItemIdsExistInItemDatabase(orderItemsDto);

        List<ItemGroup> orderItems = ItemGroupMapper.convertListOfItemGroupDtosToListOfItemGroups(orderItemsDto);

        return orderService.createOrder(customerId, orderItems);
    }
}
