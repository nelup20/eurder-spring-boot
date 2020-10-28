package com.neluplatonov.eurder.service;

import com.neluplatonov.eurder.api.dtos.itemgroupdtos.ItemGroupDto;
import com.neluplatonov.eurder.domain.ItemGroup;
import com.neluplatonov.eurder.domain.Order;
import com.neluplatonov.eurder.repository.CustomerDatabase;
import com.neluplatonov.eurder.repository.ItemDatabase;
import com.neluplatonov.eurder.repository.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class OrderService {
    private OrderDatabase orderDatabase;
    private CustomerDatabase customerDatabase;
    private ItemDatabase itemDatabase;

    @Autowired
    public OrderService(OrderDatabase orderDatabase, CustomerDatabase customerDatabase, ItemDatabase itemDatabase) {
        this.orderDatabase = orderDatabase;
        this.customerDatabase = customerDatabase;
        this.itemDatabase = itemDatabase;
    }

    public Order createOrder(String customerId, List<ItemGroup> orderItems){
        if(!customerDatabase.customerExists(customerId)) throw new IllegalArgumentException("The customer with the provided ID does not exist!");
        List<ItemGroup> orderItemsWithCorrectShippingDates = assignCorrectShippingDates(orderItems);

        Order newOrder = new Order(orderItemsWithCorrectShippingDates, customerId);

        orderDatabase.createOrder(newOrder);

        return newOrder;
    }


    public void checkIfAllItemIdsExistInItemDatabase(List<ItemGroupDto> itemGroupDtosToCheck){
        if(!itemGroupDtosToCheck.stream().allMatch(itemGroupDto -> itemDatabase.itemExists(itemGroupDto.getItemId()))) throw new IllegalArgumentException("We couldn't find an item with 1 or more of the item ID's you provided.");
    }


    private List<ItemGroup> assignCorrectShippingDates(List<ItemGroup> orderItems){
        List<ItemGroup> resultList = orderItems;

        for(ItemGroup itemGroup : resultList){
            if(itemDatabase.getItemById(itemGroup.getItemId()).getAmountInStock() >= itemGroup.getItemQuantityToOrder()){
                itemGroup.setShippingDate(LocalDate.now().plusDays(1));
            }
        }

        return resultList;
    }
}
